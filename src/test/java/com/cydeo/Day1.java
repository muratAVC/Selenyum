package com.cydeo;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
public class Day1  {

WebDriver driver;
@Before
public void firstMethot(){
    //WebDriverManager.firefoxdriver().setup();
    WebDriverManager.chromedriver().setup();
    //driver=new FirefoxDriver();
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
}
    @Test
    public void task1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver drv=new ChromeDriver();
        drv.manage().window().maximize();
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        drv.navigate().to("https://practice.cydeo.com/windows");
        String winHandle= drv.getWindowHandle();
        drv.switchTo().window(winHandle);
        Assert.assertTrue(drv.getTitle().equals("Windows"));
        WebElement clkbtn= drv.findElement(By.xpath("//*[@id='content']/div/a"));
        clkbtn.click();
        Set<String> allWinHand=drv.getWindowHandles();
        for ( String windhand: allWinHand ) {
            if (windhand.equals("New Window")) Assert.assertTrue(windhand.equals("New Window"));
        }
    }
@Test
    public void Task1_day7(){

        driver.navigate().to("https://www.amazon.com");
        ((JavascriptExecutor) driver).executeScript("window.open('http://google.com','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('http://etsy.com','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('http://facebook.com','_blank');");
        Set<String> allWindov=driver.getWindowHandles();
    Actions actions=new Actions(driver);

        for (String str: allWindov) {

            driver.switchTo().window(str);
            if (driver.getTitle().contains("Etsy")) {
                driver.findElement(By.xpath("//*[@id='logo']")).isDisplayed();
                Assert.assertTrue(driver.getTitle().contains("Etsy"));
                break;
            }

        }

    }
@Test
    public void task2_day7(){
        driver.navigate().to("https://www.amazon.com");
        ((JavascriptExecutor) driver).executeScript("window.open('http://google.com','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('http://etsy.com','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('http://facebook.com','_blank');");
        Set<String> allWindov=driver.getWindowHandles();
        BrowserUtils browserUtils=new BrowserUtils();
        browserUtils.switchWindowAndVerify(driver,"https://etsy.com","Esty");

    }
@Test
    public void task3_Day7(){
    driver.navigate().to("https://login1.nextbasecrm.com");
    String userName="helpdesk2@cybertekschool.com";
    String userPass="UserUser";
    driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys(userName);
    driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys(userPass);
    driver.findElement(By.xpath("//*[@id='login-popup']/form/div[2]/input")).click();
    System.out.println("driver.getTitle() = " + driver.getTitle());
    Assert.assertTrue(driver.getTitle().contains("Portal"));
    }
@Test
    public void login(){//task4

        String userName="helpdesk2@cybertekschool.com";
        String userPass="UserUser";
        String url="https://login1.nextbasecrm.com";
        login_crm(driver,url);
        login_crm(driver,userName,userPass);

    }

    public void login_crm(WebDriver driver,String url){
        driver.navigate().to(url);
    }
    public void login_crm(WebDriver driver,String userName,String userPass){
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys(userPass);
        driver.findElement(By.xpath("//*[@id='login-popup']/form/div[2]/input")).click();
        //System.out.println("driver.getTitle() = " + driver.getTitle());
        verifyTitle(driver,"Portal");

    }

    public void verifyTitle(WebDriver driver, String expectedTitle){//task5
        if (driver.getTitle().contains(expectedTitle)){
            Assert.assertTrue(driver.getTitle().contains(expectedTitle));
        }

    }
    @After
    public void driverQuit(){driver.quit();}
@Test
    public void drag_drop() throws InterruptedException {
    login_crm(driver,"https://practice.cydeo.com/drag_and_drop_circles");
    WebElement smallcircle=driver.findElement(By.xpath("//*[@id='draggable']"));
    WebElement bigCircle=driver.findElement(By.xpath("//*[@id='droptarget']"));
    Actions actions=new Actions(driver);
    //actions.dragAndDrop(smallcircle,bigCircle).build().perform();
    actions.moveToElement(smallcircle)
           .pause(2000)
           .clickAndHold().pause(2000)
           .clickAndHold(bigCircle).pause(1000)
           .release().perform();
    assertEquals(bigCircle.getText(),"You did great!");
    Thread.sleep(3000);
    }
    @Test
public void hover() throws InterruptedException {
    login_crm(driver,"https://practice.cydeo.com/hovers");
    List<WebElement> webelenemtList=driver.findElements(By.xpath("//div[@class='figure']"));
    Actions actions=new Actions(driver);
    for (WebElement allWebElement:webelenemtList) {
        actions.moveToElement(allWebElement).pause(3000).perform();
        System.out.println("allWebElement.getText() = " + allWebElement.getText());
    }
    Thread.sleep(4000);

}
@Test
public void vatanTest() throws InterruptedException {
    login_crm(driver,"https://www.vatanbilgisayar.com");
    WebElement tv=driver.findElement(By.xpath("//*[@id='navbar']/ul/li[4]/a"));
    Actions actions=new Actions(driver);
    actions.moveToElement(tv).pause(10000).perform();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@id='navbar']/ul/li[4]/div/div/div/ul[2]/div[2]/li[1]/a")).isDisplayed());
    Thread.sleep(5000);
}


}


