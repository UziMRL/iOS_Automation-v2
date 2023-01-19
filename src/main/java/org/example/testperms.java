package org.example;

import io.appium.java_client.remote.IOSMobileCapabilityType;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



public class testperms {
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
    public void Around_Onboard_permissions() {
        //Click on activation button
        driver.findElement(By.xpath("//*[@x='160']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UITextField']")).sendKeys("101_401_1008");
        //click on activate
        driver.findElement(By.xpath("//*[@width='130']")).click();
        //wait until success
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //move to next section
        driver.findElement(By.xpath("//*[@x='282']")).click();
        //next section
        driver.findElement(By.xpath("//*[@y='1344']")).click();
        //microphone permission
        driver.findElement(By.xpath("//*[@id='OK']")).click();
        //location services
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='Allow While Using App']")).click();
        //change to always allow
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='Change to Always Allow']")).click();
        //Motion and fitness
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='OK']")).click();
        //discoverable device
        driver.findElement(By.xpath("//*[@id='Allow']")).click();
        //make sure we see the sound level
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//*[@width='416']"));
        if (element.isDisplayed()) {
            // Assert that the element is displayed
            Assert.assertTrue(element.isDisplayed());
        } else {
            // If the element is not displayed, Fail the test
            driver.quit();
            System.out.println("Element is not displayed");
        }

    }
    @After
    public void tearDown() {
        driver.quit();
        System.out.println(ANSI_GREEN_Background + ANSI_BLACK + "Test Passed");
    }
}