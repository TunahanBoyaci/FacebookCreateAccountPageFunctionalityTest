package Day08;

import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class _01_StatusOfElement extends BaseDriver {
    @Test
    public void test1(){
        driver.get("https://www.facebook.com/");

        WebElement createButton = driver.findElement(By.xpath("//a[@role='button' and @ajaxify='/reg/spotlight/']"));
        createButton.click();

        MyMethods.myWait(1);

        WebElement firstNameInbox = driver.findElement(By.xpath("//input[@name='firstname' and @placeholder='First name']"));
        firstNameInbox.sendKeys("John");

        WebElement lastNameInbox = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastNameInbox.sendKeys("Smith");

        // Email
        MyMethods.myWait(1);
        WebElement emailInbox = driver.findElement(By.xpath("//input[@aria-label='Mobile number or email']"));
        WebElement emailConfInbox = driver.findElement(By.xpath("//input[@aria-label='Re-enter email']"));
        Assert.assertFalse(emailConfInbox.isDisplayed());

        emailInbox.sendKeys("johnsmith2@gmail.com");

        MyMethods.myWait(2);

        Assert.assertTrue(emailConfInbox.isDisplayed());
        emailConfInbox.sendKeys("johnsmith2@gmail.com");

        // Password
        WebElement passwordInbox = driver.findElement(By.xpath("//input[@data-type='password']"));
        passwordInbox.sendKeys("123789Ach.");

        // Date of Birth
        Random random = new Random();

        WebElement selectMonth = driver.findElement(By.xpath("//select[@id='month']"));
        WebElement selectDay = driver.findElement(By.xpath("//select[@id='day']"));
        WebElement selectYear = driver.findElement(By.xpath("//select[@id='year']"));

        Select selectM = new Select(selectMonth);
        Select selectD = new Select(selectDay);
        Select selectY = new Select(selectYear);

        selectM.selectByIndex(random.nextInt(12));

        List<WebElement> numberOfDays = selectD.getOptions();
        selectD.selectByIndex(random.nextInt(numberOfDays.size()));

        List<WebElement> numberOfYears = selectD.getOptions();
        selectY.selectByIndex(random.nextInt(numberOfYears.size()));

        MyMethods.myWait(1);
        WebElement genderBox = driver.findElement(By.xpath("//input[@value='2']"));
        genderBox.click();

        Assert.assertTrue(genderBox.isSelected());

        waitAndQuit();
    }
}
