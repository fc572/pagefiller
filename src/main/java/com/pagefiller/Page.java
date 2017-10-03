package com.pagefiller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class Page {

    private  WebDriver driver;

    private List<WebElement> pageElements;

    Page(WebDriver driver, List<WebElement> webElements) throws Throwable {
        this.driver = driver;
        this.pageElements = webElements;
    }

    private WebDriver getBrowser() throws Throwable {
        if(driver == null) {
            driver = ChromeBrowser.buildChromeBrowser();
        }
        return driver;
    }

    void openPageWithUrl(String url) throws InterruptedException {
        driver.navigate().to(url);
    }

    void addElementToThePage(String selectoryType, String webElementName, String cellValue) {
        WebElement webElement;

        switch (selectoryType){
            case "id":
                webElement = driver.findElement(By.id(webElementName));
                break;
            case "name":
                webElement = driver.findElement(By.name(webElementName));
                break;
            case "css":
                webElement = driver.findElement(By.cssSelector(webElementName));
                break;
            case "className":
                webElement = driver.findElement(By.className(webElementName));
                break;
            default:
                webElement = driver.findElement(By.id(webElementName));
        }
            String type = webElement.getAttribute("type");

            if(type.equalsIgnoreCase("select-one")){
                waitFor(webElement);
                Select dropdown = new Select(webElement);
                dropdown.selectByVisibleText(cellValue);
            } else if (type.equalsIgnoreCase("radio") || type.equalsIgnoreCase("checkbox") ) {
                if (webElement.getAttribute("value").equalsIgnoreCase(cellValue)) {
                    waitFor(webElement);
                    webElement.click();
                }
            } else if (type.equalsIgnoreCase("submit")) {
                waitFor(webElement);
                webElement.click();
            } else {
                waitFor(webElement);
                webElement.click();
                webElement.sendKeys(cellValue);
            }
            pageElements.add(webElement);
    }

    void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    List<WebElement> getElements() {
        return pageElements;
    }

    void close() {
        driver.close();
    }
}