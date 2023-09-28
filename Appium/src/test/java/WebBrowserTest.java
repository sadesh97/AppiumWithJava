import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

/*
 *9/27/2023 created by Sadesh Maheeshakya
 */
public class WebBrowserTest extends TestBase {

    @BeforeTest
    public void testBrowser() {

        try {
            setUp();
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            URL url = new URL("http://127.0.0.1:4723/");
            appiumDriver = new AppiumDriver(url, capabilities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        appiumDriver.get("https://facebook.com");

        //Note: If your chrome not support with driver, start appium with this command below,
        //appium --allow-insecure chromedriver_autodownload

    }

    @Test
    public void loginToFacebook() throws InterruptedException {
        WebElement email = appiumDriver.findElement(By.id("m_login_email"));
        WebElement password = appiumDriver.findElement(By.id("m_login_password"));
        WebElement loginBtn = appiumDriver.findElement(By.xpath("//button[@name='login']"));

        email.click();
        email.sendKeys("testsser@gmail.com");
        password.click();
        password.sendKeys("QWERTY@123");
        loginBtn.click();

        Thread.sleep(5000);

        WebElement errorMsg = appiumDriver.findElement(By.xpath("//div[@class='_52je']"));
        String errorText = errorMsg.getText();
        softAssert.assertEquals(errorText, "Need help with finding your account?", "Wrong error Text");
        softAssert.assertAll();

    }

    @AfterTest
    public void quitAppium() {
        finishUp();
    }


}
