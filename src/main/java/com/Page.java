package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {

    private static WebDriver driver;

    static WebDriver getBrowser() throws Throwable {
        if(driver == null) {
            driver = ChromeBrowser.buildChromeBrowser();
        }
        return driver;
    }

    public void openPageWithUrl(String url) {
        driver.navigate().to("file:///Users/44022649/Desktop/varioous/tryHtml1.html");
    }


    public WebElement createElement(String dataElement) {
        return driver.findElement(By.cssSelector(dataElement));
    }
}
