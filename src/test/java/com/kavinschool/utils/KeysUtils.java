package com.kavinschool.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class KeysUtils {

    public static void arrowLeft(final WebDriver driver, final int numOfTimesToMove) {
        final Actions action = new Actions(driver);
        for (int i = 0; i < numOfTimesToMove; i++) {
            action.sendKeys(Keys.ARROW_LEFT);
        }
        action.build().perform();
    }

    public static void arrowRight(final WebDriver driver, final int numOfTimesToMove) {
        final Actions action = new Actions(driver);
        for (int i = 0; i < numOfTimesToMove; i++) {
            action.sendKeys(Keys.ARROW_RIGHT);
        }
        action.build().perform();
    }

    public static void downArrow(final WebDriver driver, final int numOfTimesToMove) {
        final Actions action = new Actions(driver);
        for (int i = 0; i < numOfTimesToMove; i++) {
            action.sendKeys(Keys.ARROW_DOWN);
        }
        action.build().perform();
    }

    public static void upArrow(final WebDriver driver, final int numOfTimesToMove) {
        final Actions action = new Actions(driver);
        for (int i = 0; i < numOfTimesToMove; i++) {
            action.sendKeys(Keys.ARROW_UP);
        }
        action.build().perform();
    }

    public static String getOsName() {
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            return "Linux";
        } else if (os.contains("mac")) {
            return "Mac";
        } else if (os.contains("sunos")) {
            return "Solaris";
        } else {
            return "Other";
        }
    }

    public void zoomDefaultBrowser(final WebDriver driver) {
        final WebElement page = driver.findElement(By.tagName("html"));
        switch (getOsName().toLowerCase()) {
            case "mac":
                page.sendKeys(Keys.chord(Keys.META, "0"));
                break;
            case "windows":
                page.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                break;
            default:
                break;
        }
    }

    public void zoomOutBrowser(final WebDriver driver) {
        final WebElement page = driver.findElement(By.tagName("html"));
        switch (getOsName().toLowerCase()) {
            case "mac":
                page.sendKeys(Keys.chord(Keys.META, Keys.SUBTRACT));
                break;
            case "windows":
                page.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                break;
            default:
                System.out.println("OS not specified");
                break;
        }
    }

    public void zoomInBrowser(final WebDriver driver) {
        final WebElement page = driver.findElement(By.tagName("html"));
        switch (getOsName().toLowerCase()) {
            case "mac":
                page.sendKeys(Keys.chord(Keys.META, Keys.ADD));
                break;
            case "windows":
                page.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
                break;
            default:
                System.out.println("OS not specified");
                break;
        }
    }

    public void type(final WebElement field, final String text) {
        Utils.delay(1000);
        field.sendKeys(text);
    }

    public void typeAppend(final WebElement field, final String text) {
        type(field, text);
    }

    public void typePrepend(final WebElement field, final String text) {
        field.sendKeys(Keys.HOME, text);
    }

    public void typePrependTextArea(final WebElement field, final String text) {
        field.sendKeys(Keys.CONTROL, Keys.HOME, text);
    }

    public void selectAll(final WebDriver driver) {
        Actions actions = new Actions(driver);
        Keys key = null;

        switch (getOsName().toLowerCase()) {
            case "mac":
                key = Keys.COMMAND;
                break;
            case "windows":
                key = Keys.CONTROL;
                break;
            default:
                break;
        }
        actions.keyDown(key).sendKeys("a").keyUp(key).build().perform();
    }

}
