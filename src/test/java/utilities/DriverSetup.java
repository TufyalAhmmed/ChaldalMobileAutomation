package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DriverSetup {
    private static ResourceUtils resource = new ResourceUtils().getResource("config");
    private static final ThreadLocal<AndroidDriver> LOCAL_DRIVER = new ThreadLocal<>();

    public static void setDriver(AndroidDriver driver) {
        DriverSetup.LOCAL_DRIVER.set(driver);
    }

    public static AndroidDriver getDriver() {
        return LOCAL_DRIVER.get();
    }

    public static AndroidDriver setAPK() throws MalformedURLException {
        File f = new File("src/test/resources/APK");
        File fs = new File(f, resource.getString("apk"));
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", resource.getString("platformName"));
        cap.setCapability("automationName", resource.getString("automationName"));
        cap.setCapability("deviceName", resource.getString("deviceName"));
        cap.setCapability("udid", resource.getString("udid"));
        cap.setCapability("app", fs.getAbsolutePath());
//        cap.setCapability("appPackage","");
//        cap.setCapability("appActivity","");
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), cap);

    }

    public static synchronized void setAndroidDriver() throws MalformedURLException {
        AndroidDriver driver = setAPK();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(resource.getInteger("wait")));
        setDriver(driver);
    }

    //    public static synchronized void quiteAndroidDriver(Scenario scenario){
//        takeScreenShot(scenario);
////        getDriver().removeApp(resource.getString("appPackage"));
//    }
    public static synchronized void quiteAndroidDriver() {
        getDriver().removeApp(resource.getString("appPackage"));
        getDriver().quit();
    }

    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            String name = scenario.getName().replaceAll(" ", "_");
            byte[] source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(source, "image/png", name);
        }
    }
}