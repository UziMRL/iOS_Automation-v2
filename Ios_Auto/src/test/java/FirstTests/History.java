package FirstTests;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.*;
import org.openqa.selenium.By;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Regression Tests")
@Feature("FirstTests.History")
public class History {
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

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "auto");
        dc.setCapability("noReset", true);
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/Desktop/AsoundStag (06).ipa");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mrl.Asound.stag");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);


    }
    @Test
    public void Activate(){
        driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
        //enter an incorrect activation code
        driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_1191031");
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
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + "FirstTests.Permissions have been given.");
    }
    @Test
    public void History_Button() {
        //Test the text of the history button
        String Actual_Result=driver.findElement(By.xpath("//*[@id='FirstTests.History']")).getText();
        //Test the text of Quick question-Excepted result
        String Excepted_Result = "FirstTests.History";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void Open_History_Page() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //verify you are in the history page.
        String Actual_Result=driver.findElement(By.xpath("//*[@name='FirstTests.History']")).getText();
        //Test the text of the title.
        String Excepted_Result = "FirstTests.History";
        //AssertEquals that the title is history
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void History_Active_Days() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the Active Days in the circle
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Active days']")).getText();
        //Test the Text
        String Excepted_Result = "Active days";
        //AssertEquals that the Active Days Text is visible
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void History_Sunday() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the Monday in the calnedar
        String Actual_Result=driver.findElement(By.xpath("//*[@id='M']")).getText();
        //Test the text of Sunday
        String Excepted_Result = "M";
        //AssertEquals that the Day is visible
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result + "onday");
    }

    @Test
    public void History_Friday() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see Friday
        String Actual_Result=driver.findElement(By.xpath("//*[@id='F']")).getText();
        //Test the text of the Day.
        String Excepted_Result = "F";
        //AssertEquals that the Day is Friday
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result + "riday");
    }
    @Test
    public void History_First_Calendar_Day() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the First day in teh calendar
        String Actual_Result=driver.findElement(By.xpath("//*[@id='1']")).getText();
        //Test the text of the Day
        String Excepted_Result = "1";
        //AssertEquals that the Day is the first in the calendar
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result+ "st day of the month");
    }
    @Test
    public void History_Twenty_Eighth() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the 28th day of the month
        String Actual_Result=driver.findElement(By.xpath("//*[@id='28']")).getText();
        //Test the text of the day.
        String Excepted_Result = "28";
        //AssertEquals that the day is the twenty-eighth, I chose 28th incase the month is february.
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result + "th day of the month");
    }

    @Test
    public void History_Statuses_Successfully() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the successful status
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Successfully']")).getText();
        //Test the text of the status.
        String Excepted_Result = "Successfully";
        //AssertEquals that the status is successfully
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void History_Statuses_Almost() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the Almost status
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Almost']")).getText();
        //Test the text of the status.
        String Excepted_Result = "Almost";
        //AssertEquals that the status is Almost
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void History_Statuses_No_Activity() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the no activity status
        String Actual_Result=driver.findElement(By.xpath("//*[@id='No activity']")).getText();
        //Test the text of the status.
        String Excepted_Result = "No activity";
        //AssertEquals that the status is "No activity"
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void History_Statuses_Panelist_ID() {
        //open the history page.
        driver.findElement(By.xpath("//*[@id='FirstTests.History']")).click();
        //in the history page make sure you see the panelist id
        String Actual_Result=driver.findElement(By.xpath("//*[@text='Panelist ID: 1191001']")).getText();
        //Test the text of the panelist id.
        String Excepted_Result = "Panelist ID: 1191031";
        //AssertEquals that text reads out the panelist id
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
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
