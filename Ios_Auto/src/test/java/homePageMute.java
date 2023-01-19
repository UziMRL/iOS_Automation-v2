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



public class homePageMute {
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
        dc.setCapability("reportDirectory", "/Users/qa/Desktop/Reports");
        dc.setCapability("reportFormat", "pdf");
        dc.setCapability("testName", "Home page mute tests");
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

    @After
    public void tearDown() {
        driver.quit();

    }
}