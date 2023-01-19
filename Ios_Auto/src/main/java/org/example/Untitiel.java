package org.example;

import io.appium.java_client.remote.IOSMobileCapabilityType;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



public class Untitiel {
    private final String reportDirectory = "reports";
    private final String reportFormat = "xml";
    private final String testName = "Untitled";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_RED_Background = "\u001B[41m";

    public static final String ANSI_GREEN_Background = "\u001B[42m";
    // Main driver method
    private final String activationCode = "1001_401_1008";
    protected IOSDriver<IOSElement> driver = null;
    WebDriverWait wait;

    DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "00008030-001150A80A6A802E");
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/appiumstudio/original-apks/AsoundStag (06).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        dc.setCapability("instrumentApp", true);
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void Activation_code_incorrect()  {
        //Click on activation button
        driver.findElement(By.xpath("//*[@x='160']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UITextField']")).sendKeys("101_6565656");
        //click on activate
        driver.findElement(By.xpath("//*[@width='130']")).click();
        //wait untill error pops up
       driver.findElement(By.xpath("//*[@text='The activation code you entered could not be found.\n" +
               "Please verify you have the correct code and try again']")).isDisplayed();
       //print out error message
        System.out.println(ANSI_GREEN_Background+ANSI_WHITE+ driver.findElement(By.xpath("//*[@text='The activation code you entered could not be found.\n" +
                "Please verify you have the correct code and try again']")).getText());
        //dismiss the popup
        driver.findElement(By.xpath("//*[@text='OK']")).click();
    }
//    @Test
//    public void Activation_code_correct() throws InterruptedException {
//        // Tap on the "yes I would like to activate"
//        driver.findElement(By.xpath("//*[@x='160']")).click();
//        //Activation code
//        driver.findElement(By.xpath("//*[@class='UITextField']")).sendKeys(activationCode);
//        //Activate button
//        driver.findElement(By.xpath("//*[@x='466']")).click();
//        //Wait
//        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
//        //print the next screen
//        System.out.println(driver.findElement(By.xpath("//*[@id='You have been chosen to participate in a special content survey.\n" +
//                "Asound measures this device's exposure to audio content playing from any audio source. The app runs in the background and should not interfere with the mobile device's regular operation.']")));
//
//    }


    @After
    public void tearDown() {
        driver.quit();
    }
}