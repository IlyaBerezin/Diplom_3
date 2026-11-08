package org.example;

import io.qameta.allure.Description;
import org.example.drivers.Browser;
import org.example.pages.PersonalAccountPage;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.EnterToAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalAccountTest {
    private WebDriver driver;
    private EnterToAccountPage enterToAccountPage;
    private PersonalAccountPage personalAccountPage;

    private String email;
    private String password;
    private String name;
    private String token;

    public void setUp(Browser browser) {
        driver = browser.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        enterToAccountPage = new EnterToAccountPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);

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
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    @Description("При переходе по клику на «Личный кабинет» авторизованного пользователя происходит переход в личный кабинет")
    void loginEnterToAccountButton(Browser browser){
        setUp(browser);
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();
        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        assertThat(
                driver.getCurrentUrl(),
                containsString("/account/profile")
        );
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Проверка перехода по клику на «Конструктор» из личного кабинета")
    @Description("При переходе по клику на «Конструктоа» авторизованного пользователя происходит переход на главную страницу с оформлением заказа")
    void enterToConstructorOfBurger(Browser browser){
        setUp(browser);
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickConstructorButtonOfAuthUser();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Проверка перехода по клику на логотип «Stellar Burgers из личного кабинета")
    @Description("При переходе по клику на логотип «Stellar Burgers» авторизованного пользователя происходит переход на главную страницу с оформлением заказа")
    void enterToLogoStellarBurgers(Browser browser){
        setUp(browser);
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickLogoStellarsurgers();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("Проверка выхода из личного кабинета кликом Выход")
    @Description("При нажатии на кнопку ВЫХОД в личном кабинете происходит выход к форме авторизации (входа)")
    void exitAccount(Browser browser){
        setUp(browser);
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(email);
        enterToAccountPage.enterPassword(password);
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickExitOfAccount();

        assertTrue(enterToAccountPage.isExitAccount(), "Должна появиться форма входа");
    }

}
