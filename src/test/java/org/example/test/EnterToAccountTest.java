package org.example.test;

import io.qameta.allure.Description;
import org.example.User;
import org.example.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.EnterToAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.*;
import org.example.util.DriverFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnterToAccountTest {
    private WebDriver driver;
    private EnterToAccountPage enterToAccountPage;
    private UserClient userClient;

    private User user;
    private String token;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        enterToAccountPage = new EnterToAccountPage(driver);
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
    @DisplayName("Вход в аккаунт по кнопке Войти в аккаунт")
    @Description("При нажатии на кнопку Войти в аккаунт и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginEnterToAccountButton(){
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @Test
    @DisplayName("Вход в аккаунт по кнопке Личный кабинет")
    @Description("При нажатии на кнопку Личный кабинет и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginPersonalAccountButton() {
       enterToAccountPage.clickPersonalAccountButton();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
       enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    @Description("При нажатии на кнопку Войти в форме регистрации и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginToRegistrationButton() {
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.clickRegistrationButtonLink();
        enterToAccountPage.clickEnterButtonLink();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    @Description("При нажатии на кнопку Восстановить пароль и Войти, и вводе корректого логина и пароля появляется кнопка Оформить заказ")
    void loginToRecoverPasswordButton() {
        enterToAccountPage.clickEnterToAccountButton();
        enterToAccountPage.clickRecoverPasswordButtonLink();
        enterToAccountPage.clickEnterFormRecoverPasswordButtonLink();
        enterToAccountPage.enterEmail(user.getEmail());
        enterToAccountPage.enterPassword(user.getPassword());
        enterToAccountPage.clickEnterButton();

        assertTrue(enterToAccountPage.isLoggedIn(), "Должна появиться кнопка Оформить заказ");
    }
}
