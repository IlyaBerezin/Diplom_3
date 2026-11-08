package org.example;

import io.qameta.allure.Description;
import org.example.drivers.Browser;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.BASE_URL;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    private String name;
    private String email;
    private String password;

    public void setUp(Browser browser) {
        driver = browser.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);

        // Уникальные данные на каждый запуск
        Random random = new Random();
        name = "Иван" + random.nextInt(10000);
        email = "ivan" + random.nextInt(10000) + "@example.com";
        password = "Secret123";
    }
        @AfterEach
        public void tearDown () {
            if (driver != null) {
                driver.quit();
            }
        }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Пользователь регистрируется и попадает на страницу входа")
    public void successfulRegistrationTest(Browser browser) {
        setUp(browser);
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isLoginHeaderVisible());
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Успешная регистрация с паролем из 6ти символов")
    @Description("Пользователь регистрируется и попадает на страницу входа")
    public void successfulRegistrationSixSymbolPasswordTest(Browser browser) {
        setUp(browser);
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("Secret");
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isLoginHeaderVisible());
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Ошибка при регистрации с паролем из 5ти символов")
    @Description("Пользователь не регистрируется, появляется ошибка")
    public void failedRegistrationFiveSymbolPasswordTest(Browser browser) {
        setUp(browser);
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("Secre");
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isPasswordErrorShown());
    }
}
