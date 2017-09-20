package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    void addElementsToThePage(String url, String webElementName, String cellValue) {
        openPageWithUrl(url);
        System.out.println("Element name " + webElementName);
        WebElement webElement = driver.findElement(By.name(webElementName));
        webElement.sendKeys(cellValue);
        webElements.add(webElement);
    }
}