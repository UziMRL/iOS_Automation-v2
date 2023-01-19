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
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



public class Untitiel {
    private final String reportDirectory = "reports";
    private final String reportFormat = "xml";
    private final String testName = "Untitled";

    private final String activationCode = "1001_401_1008";
    protected IOSDriver<IOSElement> driver = null;
    WebDriverWait wait;

    DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "00008030-0012105A0C53402E");
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/appiumstudio/original-apks/AsoundStag (06).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        dc.setCapability("instrumentApp", true);
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void Activation_code_incorrect()  {
        driver.findElement(By.xpath("//*[@name='Yes, I would like to Activate']")).click();
        //Activation code
        driver.findElement(By.xpath("//*[@class='_UITextFieldCanvasView']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='_UITextFieldCanvasView']")).sendKeys(activationCode);

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //Activate button
        driver.findElement(By.xpath("//*[@id='Activate']")).click();
        //wait
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //Locate_popup_Wrong_Code
        driver.findElement(By.xpath("//*[@id='Asound']")).isDisplayed();
        System.out.println(driver.findElement(By.xpath("//*[@id='Asound']")).getText());

    }
    @Test
    public void Activation_code_correct() throws InterruptedException {
        // Tap on the "yes i would like to activate"
        driver.findElement(By.xpath("//*[@class='_TtCOCV7SwiftUI11DisplayList11ViewUpdater8Platform13CGDrawingView']")).click();
        //Activation code
        driver.findElement(By.xpath("//*[@class='_UITextFieldCanvasView']"));
        driver.findElement(By.xpath("//*[@class='_UITextFieldCanvasView']")).sendKeys("4573894567384");
        //Activate button
        driver.findElement(By.xpath("//*[@x='466']")).click();
        //Wait
        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        //print the next screen
        System.out.println(driver.findElement(By.xpath("//*[@id='You have been chosen to participate in a special content survey.\n" +
                "Asound measures this device's exposure to audio content playing from any audio source. The app runs in the background and should not interfere with the mobile device's regular operation.']")));

    }


    @After
    public void tearDown() {
        driver.quit();
    }
}