package com.anonymity.scraping.mutualfundanalysis.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ScrapyUtils {
    public static WebDriver getDriver() {
        // Chrome version 125.0.6422.142
        System.setProperty("webdriver.chrome.driver", "./chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static void quit(WebDriver driver) {
        driver.quit();
    }
}
