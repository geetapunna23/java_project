# Java Selenium Automation Project

## Overview

This project is a simple **Java + Selenium automation program** that:

* Launches a web browser
* Navigates to a Products page
* Verifies the page has loaded successfully by checking the page title and key elements
* Looks for highest prices item and adds to cart

It demonstrates core Selenium concepts including **browser setup, navigation, and basic validation**.

---

## Prerequisites

Make sure you have the following installed on your machine:

* **Git**
* **Java JDK 11 or later**
* **Maven**
* **Google Chrome**
* **ChromeDriver** (compatible with your Chrome version and available on your `PATH`)


## Setup

Clone the repository and navigate into the project directory:

```bash
git clone <JAVA_PROJECT>
cd <SeleniumDemo>
```

Maven will automatically download all required dependencies during the build:

```bash
mvn clean install
```
---

## Run the Selenium Test

Run the Selenium automation test (this will compile the project and launch the browser):

```bash
mvn compile exec:java
```

The test is executed via a Java `main()` method using the Maven Exec plugin and will automatically open the browser.

---

## Notes

* The browser will open in **UI mode** (not headless) unless configured otherwise
* Ensure your ChromeDriver version matches your installed Chrome browser
* The main entry point for the test is:

  * `com.example.SimpleTest`

---

## Troubleshooting

* Ensure you are using the correct Java version
* Verify `chromedriver` is accessible from the command line
* Run Maven with debug output if needed:

```bash
mvn compile exec:java -X
```

If you encounter issues, please check the console output for detailed error messages.
