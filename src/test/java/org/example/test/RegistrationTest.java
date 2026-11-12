package org.example.test;

import io.qameta.allure.Description;
import org.example.User;
import org.example.UserClient;
import org.example.util.DriverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.example.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.example.constants.Constants.BASE_URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private UserClient userClient;

    private User user;
    private String token;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.create();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        userClient = new UserClient();

        user = User.getRandom();
    }
        @AfterEach
        void tearDown() {
            if (user != null) {
                try {
                    String token = userClient.login(user)
                            .then()
                            .extract()
                            .path("accessToken");
                    if (token != null) {
                        userClient.delete(token);
                    }
                } catch (Exception e) {
                    // пользователь не создался
                }
                if (driver != null) {
                    driver.quit();
                }
            }
        }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Пользователь регистрируется и попадает на страницу входа")
    public void successfulRegistrationTest() {
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isLoginHeaderVisible());
    }
    @Test
    @DisplayName("Успешная регистрация с паролем из 6ти символов")
    @Description("Пользователь регистрируется и попадает на страницу входа")
    public void successfulRegistrationSixSymbolPasswordTest() {
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword("Secret");
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isLoginHeaderVisible());
    }
    @Test
    @DisplayName("Ошибка при регистрации с паролем из 5ти символов")
    @Description("Пользователь не регистрируется, появляется ошибка")
    public void failedRegistrationFiveSymbolPasswordTest() {
        registrationPage.clickAuthLink();
        registrationPage.clickRegistrationLink();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword("Secre");
        registrationPage.clickRegistrationButton();

        assertEquals(true,
                registrationPage.isPasswordErrorShown());
    }
}
