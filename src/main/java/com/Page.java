package com;

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

    void openPageWithUrl(String url) {
        driver.navigate().to(url);
    }

    void addElementsToThePage(String webElementName, String cellValue) {
        List<WebElement>  webElements = driver.findElements(By.name(webElementName));


        for (WebElement element:webElements) {
            System.out.println("TYPE OF ATTRIBUTE " + element.getAttribute("type"));
            String type = element.getAttribute("type");

            if(type.equalsIgnoreCase("select-one")){
                Select dropdown = new Select(element);
                dropdown.selectByVisibleText(cellValue);
            } else if (type.equalsIgnoreCase("radio") || type.equalsIgnoreCase("checkbox") ) {
                if (element.getAttribute("value").equalsIgnoreCase(cellValue)) {
                    element.click();
                }
            } else {
                element.sendKeys(cellValue);
            }
            pageElements.add(element);
        }
    }

    void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    List<WebElement> getElements() {
        return pageElements;
    }
}