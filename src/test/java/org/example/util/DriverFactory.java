package org.example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver create() {
        String browserName = System.getProperty("browser", "chrome");

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "yandex":
                WebDriverManager.chromedriver().browserVersion("152").setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Браузер " + browserName + " не поддерживается");
        }
    }
}
