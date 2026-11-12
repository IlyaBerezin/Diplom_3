package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // кнопка на главной странице "Личный кабинет"
    private final By personalAccountButtonOfAuthUser = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    // тнкстовый объект в личном кабинете "В этом разделе вы можете изменить свои персональные данные"
    private final By textInPersonalAccountForTest = By.xpath("//p[normalize-space()='В этом разделе вы можете изменить свои персональные данные']");
    private final By consrtuctorButton = By.xpath("//p[normalize-space()='Конструктор']");
    //private final By placeOrderButton = By.xpath("//button[normalize-space()='Оформить заказ']");
    private final By logoStellarBurgers = By.xpath("//*[contains(@class, 'AppHeader_header__logo__')]");
    private final By exitButtonOfAccount = By.xpath("//button[normalize-space()='Выход']");


    public PersonalAccountPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Нажимаем кнопку «Личный кабинет» авторизованного пользователя")
    public PersonalAccountPage clickPersonalAccountButtonOfAuthUser() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButtonOfAuthUser)).click();
        // После клика ждём надпись «Вход» — признак, что мы на странице логина
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInPersonalAccountForTest));
        return this;
    }

    @Step("Нажимаем кнопку «Конструктор» авторизованного пользователя из личного кабинета")
    public void clickConstructorButtonOfAuthUser() {
        wait.until(ExpectedConditions.elementToBeClickable(consrtuctorButton)).click();
    }

    @Step("Нажимаем логотип «Stellars Burger» авторизованного пользователя из личного кабинета")
    public void clickLogoStellarsurgers() {
        wait.until(ExpectedConditions.elementToBeClickable(logoStellarBurgers)).click();
    }

    @Step("Нажимаем на ВЫХОД авторизованного пользователя из личного кабинета")
    public void clickExitOfAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(exitButtonOfAccount)).click();
    }

}
