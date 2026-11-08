package org.example;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.EnterToAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.example.drivers.Browser;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnterToAccountTest {
    private WebDriver driver;
    private EnterToAccountPage enterToAccountPage;

    private String email;
    private String password;
    private String name;
    private String token;

    public void setUp(Browser browser) {
        driver = browser.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        enterToAccountPage = new EnterToAccountPage(driver);

        // Уникальные данные на каждый запуск
        Random random = new Random();
        name = "Иван" + random.nextInt(10000);
        email = "ivan" + random.nextInt(10000) + "@example.com";
        password = "Secret123";

        String body = String.format(
                "{\"name\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
                name, email, password);

        token = given()
                .header("Content-type", "application/json")
                .body(body)
                .post(AUTH_URL)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }
    @AfterEach
    void tearDown() {
        if (token != null && !token.isEmpty()) {
            given()
                    .header("Authorization", token)
                    .delete(DELETE_USER_URL);
        }
        if (driver != null) {
            driver.quit();
        }
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Вход в аккаунт по кнопке Войти в аккаунт")
    @Description("При нажатии на кнопку Войти в аккаунт и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginEnterToAccountButton(Browser browser){
        setUp(browser);
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Вход в аккаунт по кнопке Личный кабинет")
    @Description("При нажатии на кнопку Личный кабинет и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginPersonalAccountButton(Browser browser) {
        setUp(browser);
       enterToAccountPage.clickPersonalAccountButton();
       enterToAccountPage.enterEmail(email);
       enterToAccountPage.enterPassword(password);
       enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    @Description("При нажатии на кнопку Войти в форме регистрации и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginToRegistrationButton(Browser browser) {
        setUp(browser);
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.clickRegistrationButtonLink();
        enterToAccountPage.clickEnterButtonLink();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    @Description("При нажатии на кнопку Восстановить пароль и Войти, и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginToRecoverPasswordButton(Browser browser) {
        setUp(browser);
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.clickRecoverPasswordButtonLink();
        enterToAccountPage.clickEnterFormRecoverPasswordButtonLink();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
}
