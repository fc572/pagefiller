package com.pagefiller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

class ChromeBrowser extends ChromeDriver {
    static WebDriver buildChromeBrowser() throws Throwable {
        String osName = System.getProperty("os.name");

        if (osName.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "chromeDriverHome/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromeDriverHome/chromedriver.exe");
        }
        ChromeBrowser browser = new ChromeBrowser();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return browser;
    }
}