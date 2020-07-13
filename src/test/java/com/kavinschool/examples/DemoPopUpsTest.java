package com.kavinschool.examples;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoPopUpsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
         driver = new ChromeDriver();
    }

    @Test
    public void testPopups() {
        driver.get("http://kavinschool.com/playground/DemoInputs.html");

        String originalWindow = driver.getWindowHandle();

        //Check we don't have other windows open already
        assertEquals(driver.getWindowHandles().size(), 1, "Expected Window");

        WebElement createWindows = driver.findElement(By.id("winBut1"));
        createWindows.click();

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(numberOfWindowsToBe(4));


        assertEquals(driver.getWindowHandles().size(), 4, "Expected Window");

        for (String windowHandle : driver.getWindowHandles()) {
            System.out.println("windowHandle = " + windowHandle);
            if(!originalWindow.contentEquals(windowHandle)) {
                WebDriver popupDriver = driver.switchTo().window(windowHandle);
                String title = popupDriver.getTitle();
                System.out.println("title = " + title);
            }
        }

        driver.switchTo().window(originalWindow);
        WebElement closeWindows = driver.findElement(By.id("winBut2"));
        closeWindows.click();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();  // close the session
    }
}
