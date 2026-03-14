package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {

    private final By backToLoginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver){
        super(driver);
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickBackToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(backToLoginLink));
        driver.findElement(backToLoginLink).click();
    }
}
