package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    //Локаторы полей
    private final By authorizationButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    private final By registrationLink = By.className("Auth_link__1fOlj");
    // Ищем fieldset, внутри которого есть label с текстом "Имя", и берём из него input
    private final By nameInput = By.xpath("//fieldset[.//label[normalize-space()='Имя']]//input");

    // Ищем fieldset, внутри которого есть label с текстом "Email", и берём из него input
    private final By emailInput = By.xpath("//fieldset[.//label[normalize-space()='Email']]//input");

    // Пароль проще всего найти по типу, так как он единственный type="password" в форме
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By form = By.cssSelector("form[class*='Auth_form']");

    private final By registerButton = By.xpath("//button[normalize-space()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and contains(text(), 'Некорректный пароль')]");
    private final By loginHeader = By.xpath("//h2[contains(text(), 'Вход')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Step("Нажатие на ссылку авторизации Войти")
    public void clickAuthLink() {
        wait.until(ExpectedConditions.elementToBeClickable(authorizationButton)).click();
    }

    @Step("Нажатие ссылки Зарегистрироваться")
    public void clickRegistrationLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationLink)).click();
    }

    @Step("Заполнение поля имени")
    public RegistrationPage enterName(String name) {
        driver.findElement(form).findElement(nameInput).sendKeys(name);
        return this;
    }


    @Step("Заполнение поля email")
    public RegistrationPage enterEmail(String email) {
        driver.findElement(form).findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Заполенине поля пароля")
    public RegistrationPage enterPassword(String password) {
        driver.findElement(form).findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Поиск кнопки Зарегистрировать")
    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Проверяем: показана ошибка «Некорректный пароль»")
    public boolean isPasswordErrorShown() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    @Step("Проверяем, что открыта страница входа")
    public boolean isLoginHeaderVisible() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(loginHeader)).isDisplayed();
    }

}
