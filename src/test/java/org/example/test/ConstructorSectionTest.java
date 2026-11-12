package org.example.test;

import io.qameta.allure.Description;
import org.example.util.DriverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.ConstructorSectionPage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorSectionTest {
    private WebDriver driver;
    private ConstructorSectionPage constructorSectionPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        constructorSectionPage = new ConstructorSectionPage(driver);


    }
    @AfterEach
    void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    @DisplayName("Тест нажатия на раздел ингредиентов Булки")
    @Description("При нажатии на кнопку Булки отображается раздел ингредиентов Булки")
    void testClickBunsButton(){
        constructorSectionPage.clickBunsButton();
        assertTrue(constructorSectionPage.headerOfBunsIsVisible(), "Должен отображаться раздел Булки");
    }

    @Test
    @DisplayName("Тест нажатия на раздел ингредиентов Соусы")
    @Description("При нажатии на кнопку Соусы отображается раздел ингредиентов Соусы")
    void testClickSaucesButton(){
        constructorSectionPage.clickSauceButton();
        assertTrue(constructorSectionPage.headerOfSauceIsVisible(), "Должен отображаться раздел Соусы");
    }

    @Test
    @DisplayName("Тест нажатия на раздел ингредиентов Начинки")
    @Description("При нажатии на кнопку Начинки отображается раздел ингредиентов Начинки")
    void testClickFillingButton(){
        constructorSectionPage.clickFillingsButton();
        assertTrue(constructorSectionPage.headerOfFillingsIsVisible(), "Должен отображаться раздел Начинки");
    }
}
