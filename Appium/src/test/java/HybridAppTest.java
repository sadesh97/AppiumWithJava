import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;

/*
 *9/27/2023 created by Sadesh Maheeshakya
 */

//Used app available on Resources folder
public class HybridAppTest extends TestBase {

    @BeforeTest
    public static void openApp() {

        try {
            setUp();

            capabilities.setCapability("appPackage", "com.wdiodemoapp");
            capabilities.setCapability("appActivity", ".MainActivity");
            URL url = new URL("http://127.0.0.1:4723/");
            androidDriver = new AndroidDriver(url, capabilities); //used androidDriver for context change (native/ WEB)

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void hybridAppActivity() throws InterruptedException {

        System.out.println("Current Context: " + androidDriver.getContext());
        System.out.println("Current Handles: " + androidDriver.getContextHandles());
        WebElement webViewBtn = androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc='Webview']/android.widget.TextView[1]"));
        webViewBtn.click();
        Thread.sleep(4000);

        //get context handles
        Set<String> handles = androidDriver.getContextHandles();
        //set context handles for WebView hybrid
        androidDriver.context((String) handles.toArray()[1]);
        Thread.sleep(1000);
        System.out.println("Current context after switch: " + androidDriver.getContext());

        WebElement menuBtn = androidDriver.findElement(By.xpath("//button[@aria-label='Toggle navigation bar']//*[name()='svg']"));
        menuBtn.click();
        WebElement themeChangerBtn = androidDriver.findElement(By.xpath("//button[@class='clean-btn toggleButton_gllP']/ancestor::div[@class='toggle_vylO margin-right--md']"));
        themeChangerBtn.click();
        WebElement closeSign = androidDriver.findElement(By.xpath("//button[@class='clean-btn navbar-sidebar__close']/*[name()='svg']"));
        closeSign.click();
        Thread.sleep(1000);

        //Revert back to native
        androidDriver.context("NATIVE_APP");
        androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc='Login']/android.widget.TextView[1]")).click();
        Thread.sleep(1000);
        WebElement loginSection = androidDriver.findElement(By.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));
        softAssert.assertTrue(loginSection.isDisplayed(), "Not switched to native APP correctly");
        softAssert.assertAll();

    }

    @AfterTest
    public void quitAppium() {
        finishUp();
    }

}
