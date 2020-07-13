package com.kavinschool.examples;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoInputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //Provide your chromedriver path
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,"/Users/kang/tools/web_drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testDemoInputs() throws InterruptedException {
        driver.get("http://kavinschool.com/playground/DemoInputs.html");
        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstName.sendKeys("Kangs");

        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Pass");

        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("kangs.pass@kavinschool.com");

        chooseSex("male");

        WebElement training = driver.findElement(By.xpath("//select[@id='course']"));
        Select course = new Select(training);
        course.selectByIndex(1);
        Thread.sleep(1000);
        course.selectByValue("angular");
        Thread.sleep(1000);
        course.selectByVisibleText("NodeJS");

        WebElement edit = driver.findElement(By.id("edit"));
        Assert.assertTrue(edit.isEnabled(), "Edit should be enabled");

        WebElement disableEdit = driver.findElement(By.id("enable"));
        disableEdit.click();

        edit = driver.findElement(By.id("edit"));
        Assert.assertFalse(edit.isEnabled(), "Edit not disabled");

        checkVehicleType("Bike");
        checkVehicleType("Van");

        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Alert thankYou = driver.switchTo().alert();
        Assert.assertEquals(thankYou.getText(),"Thank You for submitting!!");
        thankYou.accept();

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close(); // close the window
        driver.quit();  // close the session
    }

    private void chooseSex(final String sexType) {
        WebElement sex = driver.findElement(By.xpath("//input[@value='" + sexType.toLowerCase() + "']"));
        if (!sex.isSelected()) {
            sex.click();
        }
    }

    private void checkVehicleType(final String vehicleType) {
        WebElement vehicle = driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + vehicleType + "']"));
        if (!vehicle.isSelected()) {
            vehicle.click();
        }
    }


}
