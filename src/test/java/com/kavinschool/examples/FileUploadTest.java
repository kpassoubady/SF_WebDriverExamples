package com.kavinschool.examples;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;

public class FileUploadTest extends BaseTest {

    private static final String BASE_URL = "https://www.plusresources.org/noah/user/create_form/";

    @Test
    public void testPictureUpload() throws URISyntaxException {
        driver.get(BASE_URL);
        WebElement picture1 = driver.findElement(By.xpath("//input[@type='file' and @name='col_12']"));
        picture1.sendKeys(getAbsPath("logo_kavinschool.png"));

        WebElement picture2 = driver.findElement(By.xpath("//input[@type='file' and @name='col_13']"));
        picture2.sendKeys(getAbsPath("logo_kavin_school1.jpg"));

        WebElement picture3 = driver.findElement(By.xpath("//input[@type='file' and @name='col_14']"));
        picture3.sendKeys(getAbsPath("logo_kavin_school2.jpg"));

    }

    private String getAbsPath(final String fileName) throws URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        File file = Paths.get(resource.toURI()).toFile(); // return a file
        return Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }
}
