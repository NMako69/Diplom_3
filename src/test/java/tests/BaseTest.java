package tests;

import api.UserClient;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CreateUser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

public abstract class BaseTest {

    protected WebDriver driver;
    protected Faker faker;

    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ForgotPasswordPage forgotPasswordPage;

    protected UserClient userClient;
    protected String token;

    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Данные пользователя
    protected String testEmail;
    protected String testPassword;
    protected String testName;

    @Before
    public void setUp() {

        // baseURI для RestAssured
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";

        // настройка браузера
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
        } else {
            WebDriverManager.chromedriver().setup();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        faker = new Faker();

        // инициализация страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        // генерация данных пользователя
        testName = faker.name().firstName();
        testEmail = faker.internet().safeEmailAddress();
        testPassword = faker.internet().password(6, 12);

        // создание пользователя через API
        userClient = new UserClient();

        CreateUser user = new CreateUser(
                testEmail,
                testPassword,
                testName
        );

        Response response = userClient.createUser(user);

        token = response.then()
                .extract()
                .path("accessToken");
    }

    @After
    public void tearDown() {

        // удаляем пользователя через API
        if (token != null) {
            userClient.deleteUser(token)
                    .then()
                    .statusCode(202);
        }

        // закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}