package com.cydeo;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class BrowserUtils {

    public void switchWindowAndVerify(WebDriver driver, String expectedInUrl, String expectedTitle){
        Set<String> allHandles=driver.getWindowHandles();
        for (String allhand:
             allHandles) {
            driver.switchTo().window(allhand);
            if (driver.getCurrentUrl().contains(expectedInUrl)){
                Assert.assertTrue(driver.getTitle().equals(expectedTitle));
                break;
            }
        }



    }
}
