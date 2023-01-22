import FirstTests.*;
import org.testng.TestNG;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { Activation.class, Permissions.class, homePageMute.class, History.class, InformationUI.class, InformationFunctionality.class });
        testng.run();
    }
}
