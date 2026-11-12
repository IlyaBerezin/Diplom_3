package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ConstructorSectionPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By buttonOfBuns = By.xpath("//div[normalize-space()='Булки']");
    private final By buttonOfSauces = By.xpath("//div[normalize-space()='Соусы']");
    private final By buttonOfFillings= By.xpath("//div[normalize-space()='Начинки']");
    private final By tittleOfBuns = By.xpath("//span[normalize-space()='Булки']/ancestor::div[contains(@class, 'tab_type_current')]");
    private final By tittleOfSauce = By.xpath("//span[normalize-space()='Соусы']/ancestor::div[contains(@class, 'tab_type_current')]");
    private final By tittleOfFillings = By.xpath("//span[normalize-space()='Начинки']/ancestor::div[contains(@class, 'tab_type_current')]");


    public ConstructorSectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие на кнопку Булки")
    public void clickBunsButton() {
        //wait.until(ExpectedConditions.elementToBeClickable(buttonOfBuns)).click();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                wait.until(ExpectedConditions.presenceOfElementLocated(buttonOfBuns)));
    }

    @Step("Нажатие на кнопку Соусы")
    public void clickSauceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonOfSauces)).click();
    }

    @Step("Нажатие на кнопку Начинки")
    public void clickFillingsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonOfFillings)).click();
    }

    @Step("Проверяем, что произошел переход (виден заголовок ингредиентов Булки)")
    public boolean headerOfBunsIsVisible() {
        // заголовок ингредиентов Булки стал виден
        wait.until(ExpectedConditions.visibilityOfElementLocated(tittleOfBuns));
        return true;
    }

    @Step("Проверяем, что произошел переход (виден заголовок ингредиентов Соусы)")
    public boolean headerOfSauceIsVisible() {
        // заголовок ингредиентов Булки стал виден
        wait.until(ExpectedConditions.visibilityOfElementLocated(tittleOfSauce));
        return true;
    }

    @Step("Проверяем, что произошел переход (виден заголовок ингредиентов Начинки)")
    public boolean headerOfFillingsIsVisible() {
        // заголовок ингредиентов Булки стал виден
        wait.until(ExpectedConditions.visibilityOfElementLocated(tittleOfFillings));
        return true;
    }
}
