package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By BunsButton = By.xpath("//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab')]");
    private final By SaucesButton = By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab')]");
    private final By FillingsButton = By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public void clickEnterAccountButton(){
        wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton));
        driver.findElement(enterAccountButton).click();
    }

    @Step("Нажать на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton(){
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isOrderButtonDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    @Step("Нажать на вкладку Булки")
    public void clickBunsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(BunsButton));
        driver.findElement(BunsButton).click();
    }

    @Step("Нажать на вкладку Соусы")
    public void clickSaucesButton(){
        wait.until(ExpectedConditions.elementToBeClickable(SaucesButton));
        driver.findElement(SaucesButton).click();
    }

    @Step("Нажать на вкладку Начинки")
    public void clickFillingsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(FillingsButton));
        driver.findElement(FillingsButton).click();
    }

    @Step("Проверить, что вкладка {tabName} активна")
    public boolean isTabActive(String tabName){
        By tabLocator;
        switch(tabName){
            case "Булки": tabLocator = BunsButton; break;
            case "Соусы": tabLocator = SaucesButton; break;
            case "Начинки": tabLocator = FillingsButton; break;
            default: throw new IllegalArgumentException("Неверное имя вкладки");
        }
        return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }
}
