import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

/*
 *9/1/2023 created by Sadesh Maheeshakya
 */
public class NativeCalculatorTest extends TestBase {

    @BeforeTest
    public static void openApp() {

        try {
            setUp();

            capabilities.setCapability("appPackage", "com.miui.calculator");
            capabilities.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");
            URL url = new URL("http://127.0.0.1:4723/");
            appiumDriver = new AppiumDriver(url, capabilities);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //trying app

// WebElement tabCal = appiumDriver.findElement(By.id("com.miui.calculator:id/ic_float_btn"));

    @Test
    public void calculatorTest() throws InterruptedException {

        appiumDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout")).click();
        WebElement seven = appiumDriver.findElement(By.id("com.miui.calculator:id/btn_7_s"));
        WebElement plus = appiumDriver.findElement(By.id("com.miui.calculator:id/btn_plus_s"));
        WebElement two = appiumDriver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        WebElement equalSign = appiumDriver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));

        seven.click();
        plus.click();
        two.click();
        equalSign.click();
        Thread.sleep(2000);

        WebElement answer = appiumDriver.findElement(By.id("com.miui.calculator:id/result"));
        String results = answer.getText();
        softAssert.assertEquals(results.replace("= ", ""), "9", "Wrong results");
        softAssert.assertAll();

    }

    @AfterTest
    public void quitAppium() {
        finishUp();
    }

}
