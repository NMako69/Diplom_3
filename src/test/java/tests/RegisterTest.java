package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя через UI")
    @Description("Проверяем, что регистрация через форму работает")
    public void successfulRegistrationTest() {
        driver.get(BASE_URL + "register");

        // Используем уже сгенерированные данные пользователя из BaseTest
        String uiName = faker.name().firstName();
        String uiEmail = faker.internet().emailAddress();
        String uiPassword = faker.internet().password(6, 12);

        registerPage.setUsername(uiName);
        registerPage.setEmail(uiEmail);
        registerPage.setPassword(uiPassword);
        registerPage.clickRegisterButton();

        assertTrue("Заголовок 'Вход' не найден!", loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверяем, что при пароле <6 символов возникает ошибка")
    public void checkRegistrationPasswordError() {
        driver.get(BASE_URL + "register");


        String shortPassword = faker.internet().password(1, 5);

        String uiName = faker.name().firstName();
        String uiEmail = faker.internet().emailAddress();

        registerPage.setUsername(uiName);
        registerPage.setEmail(uiEmail);
        registerPage.setPassword(shortPassword);
        registerPage.clickRegisterButton();

        assertEquals("Сообщение не возникло!", "Некорректный пароль", registerPage.getPasswordErrorMessage());
    }
}