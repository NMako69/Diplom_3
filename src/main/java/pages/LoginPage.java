package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    private final By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By fieldPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By loginHeader = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @Step("Проверить отображение заголовка 'Вход'")
    public boolean isLoginHeaderDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldPassword));
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}
