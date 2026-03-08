package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверяем успешный логин через главную страницу")
    public void loginViaEnterAccountButton() {

        driver.get(BASE_URL);

        mainPage.clickEnterAccountButton();

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(6,10);

        // регистрация нового пользователя
        driver.get(BASE_URL + "register");
        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        driver.get(BASE_URL);
        mainPage.clickEnterAccountButton();
        loginPage.setEmail(emailUser);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Вход не выполнен", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через  кнопку 'Личный кабинет' ")
    @Description("Проверяем успешный логин через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButton() {

        driver.get(BASE_URL);

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(6,10);

        // регистрация нового пользователя
        driver.get(BASE_URL + "register");
        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        driver.get(BASE_URL);
        mainPage.clickPersonalAccountButton();
        loginPage.setEmail(emailUser);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме регистрации")
    @Description("Проверяем успешный логин через форму регистрации")
    public void loginViaEnterButton() {

        driver.get(BASE_URL + "register");

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(6,10);

        // регистрация нового пользователя
        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        registerPage.clickEnterButton();
        loginPage.setEmail(emailUser);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме восстановления пароля")
    @Description("Проверяем успешный логин через восстановление пароля")
    public void loginViaBackToLoginLink() {

        driver.get(BASE_URL + "forgot-password");

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(6,10);

        // регистрация нового пользователя
        driver.get(BASE_URL + "register");
        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        driver.get(BASE_URL + "forgot-password");
        forgotPasswordPage.clickBackToLoginLink();
        loginPage.setEmail(emailUser);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }
}