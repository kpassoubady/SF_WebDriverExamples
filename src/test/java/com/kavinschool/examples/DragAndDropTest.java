package com.kavinschool.examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;
import com.kavinschool.utils.JsUtils;

public class DragAndDropTest extends BaseTest {
    private static final String BASE_URL = "http://kavinschool.com/playground/DragAndDrapExample.html";

    @Test
    public void testDragAndDrop() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        WebElement source = driver.findElement(By.id("drag1"));
        WebElement dest = driver.findElement(By.id("drop1"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,dest).release().build().perform();

        //Workaround for bug in chrome
        JsUtils.dropJS(driver,source,dest);
    }
}
