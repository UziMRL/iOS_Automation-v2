package FirstTests;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import static org.junit.Assert.assertEquals;
import org.testng.annotations.*;


@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Regression Tests")
@Feature("Info Functionality")
public class InformationFunctionality {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_RED_Background = "\u001B[41m";


    public static final String ANSI_GREEN_Background = "\u001B[42m";
    // Main driver method
    protected IOSDriver<IOSElement> driver = null;
    WebDriverWait wait;
    HashMap<String,Object>scrollObject = new HashMap<>();

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "auto");
        dc.setCapability("fullReset", true);
        dc.setCapability(MobileCapabilityType.APP, "/Users/yaron/Downloads/AsoundStag (11).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_1191008");
        //Activate Asound and wait
        driver.findElement(By.xpath("//*[@text='Activate']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        //here we have the "continue" situation, sometimes when the app finishes the permissions it gets stuck on the same page, this page isn't real and has no selectable elements.
        //so what I did here was basically, tell the app to quickly exit and reenter, that will open a broken state of the app which is just a black scree
        driver.runAppInBackground(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//*[@text='Asound']")).click();
        //after the "black screen" state is seen, close tha pp and quickly reopen it, that will finally send you to the home page
        //if the situation does not occur this code will run quickly like a knife through butter.
        driver.runAppInBackground(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//*[@text='Asound']")).click();
        //finally verify you have made it by printing the title of the home page it should be "Sound Level"
        assert driver.findElement(By.xpath("//*[@text='Sound Level']")).isDisplayed();
        //print it so we can see
        System.out.println(driver.findElement(By.xpath("//*[@text='Sound Level']")).getText());
        //now finally make sure we have passed the test by printing a nice passing message.
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + "Permissions have been given.");
    }


    @Test
    public void Information_Button() {
        //Test the text of the Information button
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Information']")).getText();
        //Test the text of the INFO BUTTON
        String Excepted_Result = "Information";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }

    @Test
    public void Information_Page_Refresh() {
        //open the Info page.
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //Refresh the info page after waiting for it to load.
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='Refresh']")).click();
        //make sure you haven't left the information page, make sure you can see the title of the info page
        String Actual_Result = driver.findElement(By.xpath("//*[@id='About']")).getText();
        String Expected_Result = "About";
        //AssertEquals
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Page_Close() {
        //open the Info page.
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //close the info page after waiting for it to load.
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='Close']")).click();
        //check that we have made it back to the home page.
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Sound Level']")).getText();
        //Test the text of the INFO BUTTON
        String Excepted_Result = "Sound Level";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }

    @Test
    public void Information_PCM(){
        //open the Info page.
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to see the PCM switch after waiting for the page to load.
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Save PCM");
        driver.executeScript("mobile:scroll", scrollObject);
        //find the switch and click it.
        WebElement switchElement = driver.findElement(By.xpath("//*[@knownSuperClass='UISwitch']"));
        switchElement.click();
        //assert that the switch has been activated.
        String Actual_Result =  switchElement.getText();
        String Expected_Result = "1";
        assertEquals(Expected_Result,Actual_Result);
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Privacy_Policy(){
        //open the Info page.
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to see the privacy policy button.
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Save PCM");
        driver.executeScript("mobile:scroll", scrollObject);
        //click the privacy and policy button.
        driver.findElement(By.xpath("//*[@id='Privacy Policy']")).click();
        //wait until the page fully loads.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //once the wait is over check the first paragraph of the privacy policy.
        String Actual_Result = driver.findElement(By.xpath("//*[@id=concat('We, at Mobile Research Labs Ltd. (\"MRL\"), are committed to respect " +
                "our Customers, their users and our Users', \"'\", ' right for privacy with regards to the use of their Personal Data. We have " +
                "adopted this privacy policy (as may be amended from time to time) (the ', '\"', 'Privacy Policy', '\"', ') to ensure you are fully informed and aware " +
                "as to the scope and nature of the Data MRL collects, " +
                "generate and/or otherwise received from or about you while you are using the App, and how is it being Processed and/or otherwise used by MRL.')]")).getText();
        String Expected_Result = "We, at Mobile Research Labs Ltd. (\"MRL\"), are committed to respect our Customers, their users and our Users' right for privacy with regards to the use of their Personal Data. We have adopted this privacy policy (as may be amended from time to time) (the \"Privacy Policy\") to ensure you are fully informed and aware as to the scope and nature of the Data MRL collects, generate and/or otherwise received from or about you while you are using the App, and how is it being Processed and/or otherwise used by MRL.";
        //assert we actually see it. note: the paragraph must stay in one line to work.
        assertEquals(Expected_Result, Actual_Result);
        //print the result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }

    @Test
    public void Information_Logs(){
        //open the Info page.
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to see the Email us button.
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Email us");
        driver.executeScript("mobile:scroll", scrollObject);
        //find the button and click it.
        driver.findElement(By.xpath("//*[@id='Email us']")).click();
        //wait for the page to load.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //check to see if the email popup appeared
        String Actual_Result =  driver.findElement(By.xpath("//*[@id='Log files from panelist: 1191031, customer: 119']")).getText();
        String Expected_Result = "Log files from panelist: 1191031, customer: 119";
        //AssertEquals
        assertEquals(Expected_Result,Actual_Result);
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
        //delete the draft and exit the popup
        driver.findElement(By.xpath("//*[@id='Mail.cancelSendButton']")).click();
        driver.findElement(By.xpath("//*[@id='Delete Draft']")).click();
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

    }}