package org.example.test;

import io.qameta.allure.Description;
import org.example.User;
import org.example.UserClient;
import org.example.pages.PersonalAccountPage;
import org.example.util.DriverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.EnterToAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalAccountTest {
    private WebDriver driver;
    private EnterToAccountPage enterToAccountPage;
    private PersonalAccountPage personalAccountPage;
    private UserClient userClient;

    private User user;
    private String token;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        enterToAccountPage = new EnterToAccountPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        userClient = new UserClient();

        user = User.getRandom();

        token = userClient.create(user)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");

    }
    @AfterEach
    void tearDown() {
            if (token != null && !token.isEmpty()) {
                userClient.delete(token);
            }
            if (driver != null) {
                driver.quit();
            }
        }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    @Description("При переходе по клику на «Личный кабинет» авторизованного пользователя происходит переход в личный кабинет")
    void loginEnterToAccountButton(){
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();
        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        assertThat(
                driver.getCurrentUrl(),
                containsString("/account/profile")
        );
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор» из личного кабинета")
    @Description("При переходе по клику на «Конструктоа» авторизованного пользователя происходит переход на главную страницу с оформлением заказа")
    void enterToConstructorOfBurger(){
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickConstructorButtonOfAuthUser();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип «Stellar Burgers из личного кабинета")
    @Description("При переходе по клику на логотип «Stellar Burgers» авторизованного пользователя происходит переход на главную страницу с оформлением заказа")
    void enterToLogoStellarBurgers(){
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickLogoStellarsurgers();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета кликом Выход")
    @Description("При нажатии на кнопку ВЫХОД в личном кабинете происходит выход к форме авторизации (входа)")
    void exitAccount(){
        enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        personalAccountPage.clickPersonalAccountButtonOfAuthUser();
        personalAccountPage.clickExitOfAccount();

        assertTrue(enterToAccountPage.isExitAccount(), "Должна появиться форма входа");
    }

}
