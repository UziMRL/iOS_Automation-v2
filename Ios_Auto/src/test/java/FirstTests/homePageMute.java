package FirstTests;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.qameta.allure.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Regression Tests")
@Feature("Info UI")

public class homePageMute {
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
    public void Mute() {
        //Click on Mute button
        driver.findElement(By.xpath("//*[@text='mic-on']")).click();
        //mute menu
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //mute asound for 6 hours
        driver.findElement(By.xpath("//*[@text='For 6 hours ' and @top='true']")).click();
        //make sure asound has been muted
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assert driver.findElement(By.xpath("//*[@text='Off']")).isDisplayed();
        //print the sound levele which should be off
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+driver.findElement(By.xpath("//*[@text='Off']")));
        //unmute Asound
        driver.findElement(By.xpath("//*[@text='mic-off']")).click();
        //print out the dB to make sure Asound has been unmuted
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+driver.findElement(By.xpath("//*[@text='dB']")));
        //print out a success message
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + "6 hour mute test done");
    }
    @Test
    public void Mute_2() {
        //Click on Mute button
        driver.findElement(By.xpath("//*[@text='mic-on']")).click();
        //mute menu
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //mute asound until 4 am
        driver.findElement(By.xpath("//*[@text='Until 4:00AM']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //make sure asound is muted
        assert driver.findElement(By.xpath("//*[@text='mic-off']")).isDisplayed();
        //print the mute message
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+driver.findElement(By.xpath("//*[@text='Asound was paused and will resume at 4:00 AM\n" +
                "Click on the icon to start now']")).getText());
        }
        @Test
         public void Unmute(){
        //click the mute button again to unmute
        driver.findElement(By.xpath("//*[@text='mic-off']")).click();
        //verify Asound unmuted
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assert driver.findElement(By.xpath("//*[@text='mic-on']")).isDisplayed();
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + "4 AM test passed");
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