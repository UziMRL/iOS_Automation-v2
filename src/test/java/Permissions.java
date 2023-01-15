package Permissions;

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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



public class Permissions {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
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

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "auto");
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/appiumstudio/original-apks/AsoundStag (06).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }
    @Test
    public void permissions(){
        //move on to the permissions page.
        driver.findElement(By.xpath("//*[@text='Get Started']")).click();
        //make sure you have reached the permissions page.
        System.out.println( driver.findElement(By.xpath("//*[@text='Before we start']")));
        //click the continue button to begin the permissions round
        driver.findElement(By.xpath("//*[@text='Continue']")).click();
        //Allow the microphone permission
        driver.findElement(By.xpath("//*[@text='OK']")).click();
        //wait for the location services request to appear
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //give the location permission by selecting the second option.
        driver.findElement(By.xpath("//*[@text='Allow While Using App']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //allow fitness and motion by selecting the ok button
        driver.findElement(By.xpath("//*[@text='OK']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //allow notifications
        driver.findElement(By.xpath("//*[@text='Allow']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //change location permission to always allow
        driver.findElement(By.xpath("//*[@text='Change to Always Allow']")).click();
        //continue on
        if (driver.findElement(By.xpath("//*[@text='Continue']")).isDisplayed())
            driver.findElement(By.xpath("//*[@text='Continue']")).click();
        else
            driver.findElement(By.xpath("//*[@text='Sound Level']"));

//        driver.findElement(By.xpath("//*[@text='Continue']")).click();
        //finally verify you have made it by printing the title of the home page it should be "Sound Level"
        assert driver.findElement(By.xpath("//*[@text='Sound Level']")).isDisplayed();
        //print it so we can see
        System.out.println(driver.findElement(By.xpath("//*[@text='Sound Level']")).getText());
        //now finally make sure we have passed the test by printing a nice passing message.
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + "Test Passed");
    }
    @After
    public void tearDown() {
        driver.quit();

    }
}