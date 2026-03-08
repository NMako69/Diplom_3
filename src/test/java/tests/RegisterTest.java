package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что регистрация через форму работает")
    public void successfulRegistrationTest() {

        driver.get(BASE_URL + "register");

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(6,10);

        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        assertTrue("Заголовок 'Вход' не найден!", loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверяем, что при пароле <6 символов возникает ошибка")
    public void checkRegistrationPasswordError() {

        driver.get(BASE_URL + "register");

        String name = faker.name().firstName();
        String emailUser = faker.internet().emailAddress();
        String password = faker.internet().password(1,5); // короче 6 символов

        registerPage.setUsername(name);
        registerPage.setEmail(emailUser);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        assertEquals("Сообщение не возникло!", "Некорректный пароль", registerPage.getPasswordErrorMessage());
    }
}
