package FirstTests;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.*;

import org.testng.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
@Feature("Text Test")
public class TextTest{
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
            driver.findElement(By.xpath("//*[@text='Yes, I would like to Activate']")).click();
            //enter an incorrect activation code
            driver.findElement(By.xpath("//*[@class='UIATextField']")).sendKeys("119_429_1191031");
            //Activate Asound and wait
            driver.findElement(By.xpath("//*[@text='Activate']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

        @Test
        public void Activation_code_Quick_Question_text_test() {
            //Test the text of Quick question-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Quick Question']")).getText();
            //Test the text of Quick question-Excepted result
            String Excepted_Result = "Quick Question";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Activation_code_description_text_test() {
            //Test the text of first part -Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Hey there, thanks for downloading. We have a quick question before we start...']")).getText();
            //Test the text of first part -Excepted result
            String Excepted_Result = "Hey there, thanks for downloading. We have a quick question before we start...";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Activation_code_description_second_part_text_test() {
            //Test the text of second part -Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Have you been invited to join a community panel and were assigned an activation code?']")).getText();
            //Test the text of second part -Excepted result
            String Excepted_Result = "Have you been invited to join a community panel and were assigned an activation code?";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }

        @Test
        public void Activation_code_btn_text_test() {
            //Test the text of activation code btn-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Yes, I would like to Activate']")).getText();
            //Test the text of activation code btn-Excepted result
            String Excepted_Result = "Yes, I would like to Activate";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Free_member_btn_text_test() {
            //Test the text of free meter btn-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@name='No, I will use the free sound meter']")).getText();
            //Test the text of free meter btn-Excepted result
            String Excepted_Result = "No, I will use the free sound meter";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Mobile_research_labs_text_test() {
            //Test the text of Mobile research labs-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@name='©2023 Mobile Research Labs Ltd']")).getText();
            //Test the text of Mobile research labs-Excepted result
            String Excepted_Result = "©2023 Mobile Research Labs Ltd";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Photo_credit_text_test() {
            //Test the text of photo credit-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Photo by Priscilla Du Preez on Unsplash']")).getText();
            //Test the text of photo credit-Excepted result
            String Excepted_Result = "Photo by Priscilla Du Preez on Unsplash";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Enter_your_text_test() {
            //Test the text of  Enter your-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@name='Enter your']")).getText();
            //Test the text of Enter your-Excepted result
            String Excepted_Result = "Enter your";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void activation_code_text_test() {
            //Test the text of activation code-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='FirstTests.Activation Code']")).getText();
            //Test the text of activation code-Excepted result
            String Excepted_Result = "FirstTests.Activation Code";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Activate_text_test() {
            //Test the text of Activate-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Activate']")).getText();
            //Test the text of Activate-Excepted result
            String Excepted_Result = "Activate";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
        @Test
        public void Close_text_test() {
            //Test the text of Close-Actual result
            String Actual_Result=driver.findElement(By.xpath("//*[@id='Close']")).getText();
            //Test the text of Close-Excepted result
            String Excepted_Result = "Close";
            //AssertEquals
            assertEquals(Excepted_Result,Actual_Result);
            //Print the actual result
            System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

        }
    @Test
    public void Welcome_to_text_test() {
        //Test the text of Welcome to -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Welcome to']")).getText();
        //Test the text of Welcome to -Excepted result
        String Excepted_Result = "Welcome to";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Asound_text_test() {
        //Test the text of Asound -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Asound']")).getText();
        //Test the text of Asound -Excepted result
        String Excepted_Result = "Asound";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Congratulations_text_test() {
        //Test the text of Congratulations -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Congratulations!']")).getText();
        //Test the text of Congratulations-Excepted result
        String Excepted_Result = "Congratulations!";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Description_app_text_test() {
        //Test the text of Description app -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='You have been chosen to participate in a special content survey.\n" +
                "Asound measures this device’s exposure to audio content playing from any audio source. The app runs in the background and should not interfere with the mobile device’s regular operation.']")).getText();
        //Test the text of Description app-Excepted result
        String Excepted_Result = "You have been chosen to participate in a special content survey.\n" +
                "Asound measures this device’s exposure to audio content playing from any audio source. The app runs in the background and should not interfere with the mobile device’s regular operation.";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void By_continue_text_test() {
        //Test the text of By continue -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='By continuing, you agree to our']")).getText();
        //Test the text of By continue-Excepted result
        String Excepted_Result = "By continuing, you agree to our";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Eula_text_test() {
        //Test the text of Eula -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='EULA']")).getText();
        //Test the text of Eula-Excepted result
        String Excepted_Result = "EULA";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Privacy_policy_text_test() {
        //Test the text of Privacy policy -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Privacy Policy']")).getText();
        //Test the text of Privacy policy-Excepted result
        String Excepted_Result = "Privacy Policy";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void And_text_test() {
        //Test the text of And -Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='and']")).getText();
        //Test the text of And-Excepted result
        String Excepted_Result = "and";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Get_Started_text_test() {
        //Test the text of Get Started-Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Get Started']")).getText();
        //Test the text of Get Started-Excepted result
        String Excepted_Result = "Get Started";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Mobile_research_labs_Welcome_Screen_text_test() {
        //Test the text of Mobile research labs-Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@name='©2023 Mobile Research Labs Ltd']")).getText();
        //Test the text of Mobile research labs-Excepted result
        String Excepted_Result = "©2023 Mobile Research Labs Ltd";
        //AssertEquals
        assertEquals(Excepted_Result,Actual_Result);
        //Print the actual result
        System.out.println(ANSI_GREEN_Background+ANSI_BLACK+Actual_Result);

    }
    @Test
    public void Photo_credit_Welcome_Screen_text_test() {
        //Test the text of photo credit-Actual result
        String Actual_Result=driver.findElement(By.xpath("//*[@id='Photo by Priscilla Du Preez on Unsplash']")).getText();
        //Test the text of photo credit-Excepted result
        String Excepted_Result = "Photo by Priscilla Du Preez on Unsplash";
        //AssertEquals
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
