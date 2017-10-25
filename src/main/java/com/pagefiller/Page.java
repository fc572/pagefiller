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
        List<WebElement> webElements;
        WebElement webElement;
        String type;

        switch (selectoryType){
            case "id":
                webElements = driver.findElements(By.id(webElementName));
                break;
            case "name":
                webElements = driver.findElements(By.name(webElementName));
                break;
            case "css":
                webElements = driver.findElements(By.cssSelector(webElementName));
                break;
            case "className":
                webElements = driver.findElements(By.className(webElementName));
                break;
            default:
                webElements = driver.findElements(By.id(webElementName));
        }
            webElement = webElements.get(0);
            type = webElement.getAttribute("type");

            if(type.equalsIgnoreCase("select-one")){
                waitFor(webElement);
                Select dropdown = new Select(webElement);
                dropdown.selectByVisibleText(cellValue);
            } else if (type.equalsIgnoreCase("radio") || type.equalsIgnoreCase("checkbox") ) {
                for (WebElement element : webElements) {
                    if (element.getAttribute("value").equalsIgnoreCase(cellValue)) {
                        element.click();
                    }
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