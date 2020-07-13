package com.kavinschool.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Stack;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private static final Stack<String> windowHandles = new Stack<>();

    public static void delay(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeCurrentWindowAndSwitchToParentWindow(WebDriver driver) {
        driver.close();
        driver.switchTo().window(windowHandles.pop());
    }

    public static void switchToNewWindow(WebDriver driver) {
        String curWin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(curWin) && !windowHandles.contains(curWin)) {
                windowHandles.push(curWin);
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public static String closeAlertAndGetItsText(WebDriver driver, final boolean acceptNextAlert) {
        try {
            final Alert alert = driver.switchTo().alert();
            final String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } catch (final Exception ex) {
            System.out.println("Alert Not found");
            return null;
        }
    }
}
