package FirstTests;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Regression Tests")
@Feature("FirstTests.Activation")
public class Activation {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_RED_Background = "\u001B[41m";

    public static final String ANSI_GREEN_Background = "\u001B[42m";
    // Main driver method
    protected IOSDriver<IOSElement> driver = null;
    WebDriverWait wait;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "auto");
        dc.setCapability("fullReset", true);
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/Desktop/AsoundStag (06).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void Activation_incorrect() {
        //Click on activation button
        driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_11910s31");
        //Activate Asound and wait
        driver.findElement(By.xpath("//*[@text='Activate']")).click();
        //verify you have made it by printing the following code
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='FirstTests.Activation Code Error']")).isDisplayed();
        String Excepted="FirstTests.Activation Code Error";
        String Actual= driver.findElement(By.xpath( "//*[@id='FirstTests.Activation Code Error']")).getText();
        assertEquals(Actual,Excepted);

        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual);

    }

    @Test
    public void Activation_cancel_BTN() {
        //Click on activation button
        driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_1191031");
        //Cancel Activate Asound and wait
        driver.findElement(By.xpath("//*[@id='Close']")).click();
        //verify you have made it by printing the following code
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String expected = "Yes, I would like to Activate";
        String actual = driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).getText();
        assertEquals(expected, actual);
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+actual);

    }

    @Test
    public void Activation_correct() {
        //Click on activation button
        driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_1191031");
        //Activate Asound and wait
        driver.findElement(By.xpath("//*[@text='Activate']")).click();
        //verify you have made it by printing the following code
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String Actual=driver.findElement(By.xpath("//*[@id='Congratulations!']")).getText();
        String Excepted="Congratulations!";
        assertEquals(Actual,Excepted);
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual);
    }
    @AfterMethod
    public void screenShotError(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] decodedScreenshot = Base64.getDecoder().decode(driver.getScreenshotAs(OutputType.BASE64));
            File screenshotFile = new File("screenshot.png");
            FileOutputStream fos = new FileOutputStream(screenshotFile);
            fos.write(decodedScreenshot);
            fos.close();
            Allure.addAttachment("Screenshot", new FileInputStream(screenshotFile));
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();

    }
}
