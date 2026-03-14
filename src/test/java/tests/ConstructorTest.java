package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest{

    @Test
    @DisplayName("Переход в раздел Соусы")
    @Description("Клик по кнопке Соусы делает раздел активным")
    public void testGoToSauces(){
        driver.get(BASE_URL);
        mainPage.clickSaucesButton();
        assertTrue(mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    @Description("Клик по кнопке Начинки делает раздел активным")
    public void testGoToFillings(){
        driver.get(BASE_URL);
        mainPage.clickFillingsButton();
        assertTrue(mainPage.isTabActive("Начинки"));
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    @Description("Клик по кнопке Булки делает раздел активным")
    public void testGoToBuns(){
        driver.get(BASE_URL);
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        assertTrue(mainPage.isTabActive("Булки"));
    }
}
