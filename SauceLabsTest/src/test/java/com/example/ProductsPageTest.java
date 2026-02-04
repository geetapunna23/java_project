package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ProductsPageTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldAddHighestPricedItemToCart() {

        // Login
        driver.findElement(By.cssSelector("[data-test='username']"))
                .sendKeys("standard_user");

        driver.findElement(By.cssSelector("[data-test='password']"))
                .sendKeys("secret_sauce");

        driver.findElement(By.cssSelector("[data-test='login-button']"))
                .click();

        // Verify Products page
        WebElement titleElement = driver.findElement(By.cssSelector("[data-test='title']"));
        Assertions.assertEquals(
                "Products",
                titleElement.getText(),
                "User is not on Products page"
        );

        // Find highest priced product
        List<WebElement> priceElements =
                driver.findElements(By.cssSelector("[data-test='inventory-item-price']"));

        double highest = Double.MIN_VALUE;
        double SecondHighest = Double.MIN_VALUE;

        WebElement highestElement = null;
        WebElement SecondHighestElement = null;


        for (WebElement price : priceElements) {
            double value = Double.parseDouble(price.getText().replace("$", ""));

            if (value > highest) {
                SecondHighest = highest;
                SecondHighestElement = highestElement;

                highest = value;
                highestElement = price;
                
            } else if (value > highest && value > SecondHighest) {
                SecondHighest = value;
                highestElement = price;

            }
        }

        Assertions.assertNotNull(SecondHighestElement, "No price element found");

        // Add highest priced item to cart
        WebElement parent = SecondHighestElement.findElement(
                By.xpath("./ancestor::div[contains(@class,'inventory_item')]")
        );

        WebElement addToCartButton =
                parent.findElement(By.cssSelector("button[data-test^='add-to-cart']"));
        addToCartButton.click();

        // Assert button changed to REMOVE
        WebElement removeButton =
                parent.findElement(By.cssSelector("button[data-test^='remove']"));

        Assertions.assertEquals(
                "Remove",
                removeButton.getText(),
                "Button did not change to REMOVE"
        );

        // Assert cart badge updated
        WebElement cartBadge =
                driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']"));

        Assertions.assertEquals(
                "1",
                cartBadge.getText(),
                "Cart badge count is incorrect"
        );
    }
}
