package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SimpleTest {
    public static void main(String[] args) {
        // Set ChromeDriver path (if needed)
        // System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Create WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Open a website
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //login
        WebElement username = driver.findElement(By.cssSelector("[data-test='username']"));
        username.sendKeys("standard_user");    

        WebElement password = driver.findElement(By.cssSelector("[data-test='password']"));
        password.sendKeys("secret_sauce"); 

        WebElement loginButton = driver.findElement(By.cssSelector("[data-test='login-button']"));
        loginButton.click();

        // Print title
        WebElement titleElement = driver.findElement(By.cssSelector("[data-test='title']"));
        String titleText = titleElement.getText();
        titleText.equalsIgnoreCase("Products");

        //find highest priced product

        List<WebElement> priceElements = 
        driver.findElements(By.cssSelector("[data-test='inventory-item-price']"));

        double maxPrice = Double.MIN_VALUE;  // smallest possible value
        WebElement maxPriceElement = null;

        for (WebElement price : priceElements) {
                 
            String text = price.getText();            // e.g., "$29.99"
            text = text.replace("$", "");             // remove $ symbol
            double value = Double.parseDouble(text);  // convert to double
        
            if (value > maxPrice) {
                maxPrice = value;
                maxPriceElement = price;
            }
        }

        System.out.println("Highest Price: $" + maxPrice);
        System.out.println("Element: " + maxPriceElement); // WebElement reference
        System.out.println("Text: " + maxPriceElement.getText()); // visible text

        
        // Add item to cart

        // Find the parent container of the highest-priced item
        WebElement parent = maxPriceElement.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));

        // Find and click the Add to Cart button inside that container
        WebElement addToCartButton = parent.findElement(By.cssSelector("button[data-test^='add-to-cart']"));
        addToCartButton.click();

        // Wait until button text changes to "Remove"
        WebElement removeButton = parent.findElement(By.cssSelector("button[data-test^='remove']"));


        // Assert
        String buttonText = removeButton.getText();
        System.out.println("-----------the text on the button is--------- " + buttonText);

        if (!buttonText.equals("Remove")) {
            throw new AssertionError("Expected button text to be 'Remove' but was: " + buttonText);
        }

        System.out.println("Assertion passed: Button changed to REMOVE");


        WebElement basketUpdate = driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']"));
     
        System.out.println(basketUpdate.getText());
        String badge = basketUpdate.getText();
        badge.equalsIgnoreCase("1");


        // Close browser
        driver.quit();
    }
}