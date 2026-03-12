package tests;

import com.github.javafaker.Faker;
import api.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
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
        // Настройка браузера
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

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        // Генерируем уникальные данные пользователя тестов
        testName = faker.name().firstName();
        testEmail = faker.internet().safeEmailAddress();
        testPassword = faker.internet().password(6, 12);

        // Создаем пользователя через API для логина
        userClient = new UserClient();
        Response response = userClient.createUser(testEmail, testPassword, testName);
        token = response.path("accessToken");
    }

    @After
    public void tearDown() {
        // Удаляем пользователя через API
        if (token != null) {
            userClient.deleteUser(token);
        }
        // Pfrhsdftv ,hfepth
        if (driver != null) {
            driver.quit();
        }
    }
}