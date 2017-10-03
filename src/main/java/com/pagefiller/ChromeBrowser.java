package com.pagefiller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeBrowser extends ChromeDriver {
    public static WebDriver buildChromeBrowser() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "chromeDriverHome/chromedriver");
        ChromeBrowser browser = new ChromeBrowser();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return browser;
    }
}
