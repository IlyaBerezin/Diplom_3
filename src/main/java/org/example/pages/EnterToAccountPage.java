package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class EnterToAccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    //Локаторы полей
    private final By loginHeader = By.xpath(".//h2[text()='Вход']");
    // Кнопка "Войти в аккаунт" на главной странице неавторизоавнного пользоавтеля
    private final By enterToAccountButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    // Поле email в форме входа в аккаунт
    private final By emailInput = By.xpath("//fieldset[.//label[normalize-space()='Email']]//input");
    // поле "Пароль" в форме входа в аккаунт
    private final By passwordInput = By.cssSelector("input[type='password']");
    // кнопка "Войти" в форме входа в аккаунт
    private final By enterButton = By.xpath(".//button[contains(text(), 'Войти')]");
    // кнопка в профиле "Оформить заказ"
    private final By createOrderButton = By.xpath(".//button[contains(text(), 'Оформить заказ')]");
    // кнопка на главной странице "Личный кабинет"
    private final By personalAccountButton = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    // кнопка-ссылка Зарегистрироваться
    private final By registrationButtonLink = By.xpath("//a[normalize-space()='Зарегистрироваться']");
    // кнопка-ссылка "Войти" в форме "Регистрация" и в форме восстановления пароля
    private final By enterInFormRegistrationAndRecoverButtonLink = By.xpath("//a[normalize-space()='Войти']");
    // кнопка Восстановить пароль
    private final By recoverPasswordButtonLink = By.xpath("//a[normalize-space()='Восстановить пароль']");


    public EnterToAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Нажимаем кнопку «Войти в аккаунт» на главной")
    public EnterToAccountPage clickEnterToAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterToAccountButton)).click();
        // После клика ждём заголовок «Вход» — признак, что мы на странице логина
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        return this;
    }
    @Step("Заполняем поле «Email»: {email}")
    public EnterToAccountPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }
    @Step("Заполняем поле «Пароль»")
    public EnterToAccountPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }
    @Step("Нажимаем кнопку «Войти»")
    public void clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton)).click();
    }
    @Step("Проверяем, что произошел вход (видна кнопка оформления заказа)")
    public boolean isLoggedIn() {
        // На главной для авторизованного пользователя вместо «Войти в аккаунт» появляется кнопка «Оформить заказ»
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return true;
    }
    @Step("Нажимаем кнопку «Личный кабинет» на главной")
    public EnterToAccountPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        // После клика ждём заголовок «Вход» — признак, что мы на странице логина
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        return this;
    }

    @Step("Нажимаем кнопку «Личный кабинет» на главной")
    public void clickRegistrationButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButtonLink)).click();
    }

    @Step("Нажимаем кнопку «Войти» на странице Регистрация")
    public void clickEnterButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(enterInFormRegistrationAndRecoverButtonLink)).click();
    }

    @Step("Нажимаем кнопку «Восстановить пароль» в форме входа")
    public void clickRecoverPasswordButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordButtonLink)).click();
    }

    @Step("Нажимаем кнопку «Войти» в форме восстановления пароля после нажатия на Восстановить пароль")
    public void clickEnterFormRecoverPasswordButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(enterInFormRegistrationAndRecoverButtonLink)).click();
    }

    @Step("Проверяем, что произошел выход из личного кабинета авторизованного пользователя")
    public boolean isExitAccount() {
        // При выходе из аккаунта попадаем на страницу входа
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        return true;
    }
}
