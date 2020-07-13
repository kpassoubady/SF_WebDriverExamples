package com.kavinschool.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.testng.annotations.Optional;

public class MouseUtils {

    public static void mouseHover(final WebDriver driver, final WebElement element) {
        final Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void mouseHoverClickItem(final WebDriver driver, final WebElement hoverElement,
                                           final WebElement clickElement) {
        final Actions action = new Actions(driver);
        action.moveToElement(hoverElement).moveToElement(clickElement).click().build().perform();
    }

    public static void moveToElement(final WebDriver driver, final WebElement element) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public static void rightClick(final WebDriver driver, final WebElement element) {
        final Actions action = new Actions(driver).contextClick(element);
        action.build().perform();
    }

    public static void rightClickAndSelectOption(final WebDriver driver, final WebElement element,
                                                 final int OptionToSelect) {
        final Actions action = new Actions(driver).contextClick(element);
        for (int i = 0; i < OptionToSelect; i++) {
            action.sendKeys(Keys.ARROW_DOWN);
        }
        action.build().perform();
    }

    public static void doubleClick(final WebDriver driver, final WebElement element) {
        final Actions action = new Actions(driver);
        action.moveToElement(element).doubleClick().perform();
    }

    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement dest) throws AWTException {
        new Actions(driver).dragAndDrop(source, dest).release().build().perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public static void dragAndDropUsingPos(WebDriver driver, WebElement source, WebElement dest) {
        int x = dest.getLocation().x;
        int y = dest.getLocation().y;

        Actions actions = new Actions(driver);
        actions.moveToElement(source)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(source)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(x, y)
                .moveToElement(dest)
                .moveByOffset(x, y)
                .pause(Duration.ofSeconds(1))
                .release().build().perform();
    }

    public static void dragAndDropUsingClickAndHold(final WebDriver driver, final WebElement sourceElement,
                                                    final WebElement TargetElement) {
        final Actions action = new Actions(driver);
        final Action dragAndDrop = action.moveToElement(sourceElement).clickAndHold(sourceElement).pause(500)
                .moveToElement(TargetElement, 1, 50).release(TargetElement).pause(500).build();
        dragAndDrop.perform();
    }

    public void scrollIntoView(final WebDriver driver,final WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Utils.delay(500);
        } catch(MoveTargetOutOfBoundsException e) {
            System.out.println("Moving out of bound");
        }
    }

    public void clickAt(final WebDriver driver, final By byMethod) {
        final WebElement element = driver.findElement(byMethod);
        final Actions builder = new Actions(driver);
        final Action action = builder.moveToElement(element).click().build();
        action.perform();
    }

    public void clickAt(final WebDriver driver, final By byMethod, final @Optional("10") int xOffset,
                        final @Optional("10") int yOffset) {
        final WebElement element = driver.findElement(byMethod);
        final Actions builder = new Actions(driver);
        final Action action = builder.moveToElement(element, xOffset, yOffset).click().build();
        action.perform();
    }

    public void clickAt(final WebDriver driver, final WebElement element) {
        final Actions builder = new Actions(driver);
        final Action action = builder.moveToElement(element).click().build();
        action.perform();
    }

    public void clickAt(final WebDriver driver, final WebElement element, final @Optional("10") int xOffset,
                        final @Optional("10") int yOffset) {
        final Actions builder = new Actions(driver);
        final Action action = builder.moveToElement(element, xOffset, yOffset).click().build();
        action.perform();
    }

}
