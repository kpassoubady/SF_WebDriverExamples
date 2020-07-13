package com.kavinschool.examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;

public class AlphabetDragAndDropTest extends BaseTest {
    private static final String BASE_URL = "http://kavinschool.com/playground/drag-alphas/dist/index.html";

    @Test
    public void testDragAndDrop() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        for (char i = 'A'; i <= 'Z'; i++) {
            if (i != 'V') {
                WebElement source = driver.findElement(By.xpath("//div[@id='tile-container']//div[.='" + i + "']"));
                WebElement dest = driver.findElement(By.xpath("//header"));

                Actions actions = new Actions(driver);
                actions.dragAndDrop(source, dest).release().build().perform();
            }
        }
    }
}
