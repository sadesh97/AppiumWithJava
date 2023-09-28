import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

/*
 *9/1/2023 created by Sadesh Maheeshakya
 */
public class TestBase {

    static AppiumDriver appiumDriver;
    static AndroidDriver androidDriver;
    static SoftAssert softAssert = new SoftAssert();
    static DesiredCapabilities capabilities = new DesiredCapabilities();

    protected static void setUp() {

        capabilities.setCapability("deviceName", "KASM");
        capabilities.setCapability("udid", "597loflnlf6tkfkv"); //get by using "adb devices" command
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11 RP1A.200720.011");
        capabilities.setCapability("automationName", "UiAutomator2");

    }

    protected static void finishUp() {
        if (appiumDriver != null) appiumDriver.quit();
        if (androidDriver != null) androidDriver.quit();

    }

}
