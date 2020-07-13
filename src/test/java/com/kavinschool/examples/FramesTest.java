package com.kavinschool.examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;

public class FramesTest extends BaseTest {

    private static final String BASE_URL = "http://kavinschool.com/playground/frames.html";
    private static final String SEARCH_WORD = "Selenium";

    @Test
    public void testFrames() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        // Switch to top frame
        driver.switchTo().frame("faqs");
        WebElement about = driver.findElement(By.linkText("About"));
        about.click();

        WebElement searchInput = driver.findElement(By.id("mod_search_searchword"));
        searchInput.sendKeys(SEARCH_WORD + Keys.ENTER);

        WebElement searchWord = driver.findElement(By.xpath("//div[@class='searchintro']/p/strong"));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(searchWord, SEARCH_WORD));
    }
}
