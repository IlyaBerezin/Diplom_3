package org.example.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browser {
    CHROME {
        @Override
        public WebDriver create() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    },
    YANDEX {
        @Override
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            WebDriverManager.chromedriver().browserVersion("152").setup();
            return new ChromeDriver(options);
        }
    };

    public abstract WebDriver create();

    public static Browser fromString(String name) {
        return Browser.valueOf(name.toUpperCase());
    }
}
