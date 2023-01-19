import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;


public class InformationUI {
    private final String reportDirectory = "reports";
    private final String reportFormat = "xml";
    private final String testName = "Untitled";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_RED_Background = "\u001B[41m";
//*[@class='UIAView' and ./*[@text='Member ID']]

    public static final String ANSI_GREEN_Background = "\u001B[42m";
    // Main driver method
    protected IOSDriver<IOSElement> driver = null;
    WebDriverWait wait;
    HashMap<String,Object>scrollObject = new HashMap<>();

    DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", "/Users/qa/Desktop/Reports");
        dc.setCapability("reportFormat", "pdf");
        dc.setCapability("testName", "Information UI");
        dc.setCapability(MobileCapabilityType.UDID, "auto");
        dc.setCapability("fullReset", true);
        dc.setCapability(MobileCapabilityType.APP, "/Users/qa/Desktop/AsoundStag (06).ipa");
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
    public void Information_About() {
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //Test the text of the About section.
        String Actual_Result=driver.findElement(By.xpath("//*[@id=concat('Asound is a sound meter designed to run " +
                "in the background of your personal device giving you access to the ', \"'\", 'Sound Map', \"'\", ' of your day to day life\n" +
                "Allowing you to be aware of your everyday sound level exposure\n" +
                "\n" +
                "The information contained and gathered by Asound is not intended as health or medical advice. Please always " +
                "consult a physician or other qualified health provider regarding any questions you may have about a medical condition or health objectives.')]")).getText();
        //Test the text of the about section
        String Excepted_Result = "Asound is a sound meter designed to run in the background of your personal device giving you access to the 'Sound Map' of your day to day life\n" +
                "Allowing you to be aware of your everyday sound level exposure\n" +
                "\n" +
                "The information contained and gathered by Asound is not intended as health or medical advice. Please always consult a physician or other qualified health provider regarding any questions you may have about a medical condition or health objectives.";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);
    }
    @Test
    public void Information_MemberID(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //test the text of the Member ID, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@id='Member ID']")).getText();
        String Expected_Result = "Member ID";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_App_Version(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //test the text of the app version, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='App Version']")).getText();
        String Expected_Result = "App Version";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Latest_Update(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //test the text of the Latest Content Update, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@id='Latest Content Update']")).getText();
        String Expected_Result = "Latest Content Update";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Next_Update(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //test the text of the Next Content Update, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@id='Next Content Update']")).getText();
        String Expected_Result = "Next Content Update";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Cache(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Cache");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the cache box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='Cache']")).getText();
        String Expected_Result = "Cache";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Engine(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Engine");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the Engine box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='Engine']")).getText();
        String Expected_Result = "Engine";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Save_PCM(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Save PCM");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the Save pcm box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='Save PCM']")).getText();
        String Expected_Result = "Save PCM";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Privacy(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Privacy Policy");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the privacy policy box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='Privacy Policy']")).getText();
        String Expected_Result = "Privacy Policy";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Support(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Email us");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the Email us box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@text='Email us']")).getText();
        String Expected_Result = "Email us";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @Test
    public void Information_Copyright(){
        //open the information page
        driver.findElement(By.xpath("//*[@id='Information']")).click();
        //swipe to Cache
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Email us");
        driver.executeScript("mobile:scroll", scrollObject);
        //test the text of the Email us box, make sure it is there.
        String Actual_Result = driver.findElement(By.xpath("//*[@id='©2023 Mobile Research Labs Ltd']")).getText();
        String Expected_Result = "©2023 Mobile Research Labs Ltd";
        //assert
        assertEquals(Expected_Result,Actual_Result);
        //print the actual result
        System.out.println(ANSI_BLACK + ANSI_GREEN_Background + Actual_Result);
    }
    @After
    public void tearDown() {
        driver.quit();
    }}