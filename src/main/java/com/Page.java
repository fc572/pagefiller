package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class Page {

    private  WebDriver driver;

    private List<WebElement> webElements;

    Page(WebDriver driver, List<WebElement> webElements) throws Throwable {
        this.driver = driver;
        this.webElements = webElements;
    }

    private WebDriver getBrowser() throws Throwable {
        if(driver == null) {
            driver = ChromeBrowser.buildChromeBrowser();
        }
        return driver;
    }

    void openPageWithUrl(String url) {
        driver.navigate().to(url);
    }

    void addElementsToThePage(String webElementName, String cellValue) {
        WebElement webElement = driver.findElement(By.name(webElementName));
        webElement.sendKeys(cellValue);
        webElements.add(webElement);
    }

    void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}