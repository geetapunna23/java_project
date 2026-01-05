package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SimpleTest {
    public static void main(String[] args) {
        // Set ChromeDriver path (if needed)
        // System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Create WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Open a website
        driver.get("https://www.itv.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("cassie-pre-banner--button--container"))
);

        // Print title
        System.out.println("Page title is: " + driver.getTitle());

        // Close browser
        driver.quit();
    }
}