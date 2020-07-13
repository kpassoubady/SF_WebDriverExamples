package com.kavinschool.base;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //Provide your chromedriver path
        //System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,"/Users/kang/tools/web_drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }



    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.close(); // close the window
        driver.quit();  // close the session
    }

}
