package com.kavinschool.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.kavinschool.base.BaseTest;

public class DatePickerTest extends BaseTest {
    private static final String BASE_URL = "https://www.plusresources.org/noah/user/create_form/";

    @Test
    public void testDatePicker() {
        driver.get(BASE_URL);

        WebElement datePicker = driver.findElement(By.id("col_11_trigger"));
        datePicker.click();
        chooseDay("20");
        //datePicker = driver.findElement(By.id("col_11_trigger"));
        datePicker.click();
        chooseDayDirect("10");
    }

    public boolean chooseDay(final String pickDay) {
        for (int row = 1; row <= 5; row++) {
            for (int col = 2; col <= 8; col++) {
                String locator = "//tr[@class='daysrow'][" + row + "]/td[" + col + "]";
                WebElement day = driver.findElement(By.xpath(locator));
                String currentDay = day.getText();
                if (pickDay.equals(currentDay)) {
                    day.click();
                    System.out.println("Found:" + currentDay);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chooseDayDirect(final String pickDay) {
        String locator = "//div[@class='calendar'][2]"
                + "//tr[@class='daysrow']/td[not(contains(@class,'day wn')) and text()='" + pickDay + "']";
        WebElement day = driver.findElement(By.xpath(locator));
        day.click();
        return true;
    }
}
