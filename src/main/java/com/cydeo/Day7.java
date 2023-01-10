package com.cydeo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Day7 {

    WebDriver drv;
 @Before
    public void task1(){
     WebDriverManager.chromedriver().setup();
     drv=new ChromeDriver();
     drv.manage().window().maximize();
     drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }
}
