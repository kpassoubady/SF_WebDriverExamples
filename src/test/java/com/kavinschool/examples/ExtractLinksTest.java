package com.kavinschool.examples;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;

public class ExtractLinksTest extends BaseTest {
    private static final String BASE_URL = "http://www.kavinschool.com/content/";

    @Test
    public void testLink() {
        driver.get(BASE_URL);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String linkText = link.getText();
            String href = link.getAttribute("href");
            if (!linkText.isEmpty())
                System.out.println("linkText = " + linkText + " href = " + href);
        }
    }
}
