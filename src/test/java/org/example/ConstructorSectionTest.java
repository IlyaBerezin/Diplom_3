package org.example;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.ConstructorSectionPage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.example.drivers.Browser;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorSectionTest {
    private WebDriver driver;
    private ConstructorSectionPage constructorSectionPage;
    private String browserName;

    public void setUp(Browser browser) {
        driver = browser.create();
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
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Тест нажатия на раздел ингредиентов Булки")
    @Description("При нажатии на кнопку Булки отображается раздел ингредиентов Булки")
    void testClickBunsButton(Browser browser){
        setUp(browser);
        constructorSectionPage.clickBunsButton();
        assertTrue(constructorSectionPage.headerOfBunsIsVisible(), "Должен отображаться раздел Булки");
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Тест нажатия на раздел ингредиентов Соусы")
    @Description("При нажатии на кнопку Соусы отображается раздел ингредиентов Соусы")
    void testClickSaucesButton(Browser browser){
        setUp(browser);
        constructorSectionPage.clickSauceButton();
        assertTrue(constructorSectionPage.headerOfSauceIsVisible(), "Должен отображаться раздел Соусы");
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Тест нажатия на раздел ингредиентов Начинки")
    @Description("При нажатии на кнопку Начинки отображается раздел ингредиентов Начинки")
    void testClickFillingButton(Browser browser){
        setUp(browser);
        constructorSectionPage.clickFillingsButton();
        assertTrue(constructorSectionPage.headerOfFillingsIsVisible(), "Должен отображаться раздел Начинки");
    }
}
