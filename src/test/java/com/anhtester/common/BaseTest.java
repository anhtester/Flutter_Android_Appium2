package com.anhtester.common;

import com.anhtester.constants.ConfigData;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.SystemHelpers;
import com.anhtester.listeners.TestListener;
import com.anhtester.pages.BasePage;
import com.anhtester.utils.LogUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.FlutterDriverOptions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    private AppiumDriverLocalService service;
    public FlutterFinder finder;

    @BeforeSuite
    public void runAppiumServer() {
        if (ConfigData.AppiumDriverLocalService.trim().toLowerCase().equals("true")) {
            //Kill process on port
            SystemHelpers.killProcessOnPort(ConfigData.PORT);

            //Build the Appium service
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress(ConfigData.IP_ADDRESS);
            builder.usingPort(Integer.parseInt(ConfigData.PORT));
            builder.withArgument(() -> "--use-plugins", "appium-reporter-plugin,execute-driver,element-wait,gestures,device-farm,appium-dashboard")
                    .withArgument(() -> "-ka", "800")   // Set keep-alive timeout
                    .withArgument(() -> "-pa", "/")     // Set the path
                    .withArgument(() -> "--plugin-device-farm-platform", "android")
                    .withArgument(() -> "--plugin-element-wait-timeout", "10000")
                    .withArgument(() -> "--plugin-element-wait-interval-between-attempts", "200");

            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info"); // Set log level (optional)
            builder.withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigData.TIMEOUT_SERVICE)));

            //Start the server with the builder
            service = AppiumDriverLocalService.buildService(builder);
            service.start();

            if (service.isRunning()) {
                LogUtils.info("##### Appium server started on " + ConfigData.IP_ADDRESS + ":" + ConfigData.PORT + " with plugins.");
            } else {
                LogUtils.error("Failed to start Appium server.");
            }

        }
    }

    @BeforeTest
    public void setUpDriver() {
        AppiumDriver driver;
        //UiAutomator2Options option;
        FlutterDriverOptions option;

        option = new FlutterDriverOptions();

        System.out.println("***SERVER ADDRESS: " + ConfigData.IP_ADDRESS);
        System.out.println("***SERVER POST: " + Integer.parseInt(ConfigData.PORT));

        option.setPlatformName(ConfigData.getValueJsonConfig("platformName"));
        option.setPlatformVersion(ConfigData.getValueJsonConfig("platformVersion"));
        option.setAutomationName(ConfigData.getValueJsonConfig("automationName"));
        //option.setDeviceName(ConfigData.getValueJsonConfig("deviceName"));
        //option.setAppPackage(ConfigData.getValueJsonConfig("appPackage"));
        //option.setAppActivity(ConfigData.getValueJsonConfig("appActivity"));
        option.setNoReset(Boolean.parseBoolean(ConfigData.getValueJsonConfig("noReset")));
        option.setFullReset(Boolean.parseBoolean(ConfigData.getValueJsonConfig("fullReset")));
        //option.setAutoGrantPermissions(Boolean.parseBoolean(ConfigData.getValueJsonConfig("autoGrantPermissions")));
        option.setCapability("forceAppLaunch", true);

        option.setCapability("deviceName", ConfigData.getValueJsonConfig("deviceName"));
        option.setCapability("appPackage", ConfigData.getValueJsonConfig("appPackage"));
        option.setCapability("appActivity", ConfigData.getValueJsonConfig("appActivity"));
        //option.setCapability("autoGrantPermissions", Boolean.parseBoolean(ConfigData.getValueJsonConfig("autoGrantPermissions")));


        try {
            driver = new AppiumDriver(new URL("http://" + ConfigData.IP_ADDRESS + ":" + ConfigData.PORT), option);
            DriverManager.setDriver(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        finder = new FlutterFinder(DriverManager.getDriver());
    }

    @AfterTest
    public void tearDownDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }
        if (service != null && service.isRunning()) {
            service.stop();
            LogUtils.info("##### Appium server stopped.");
        }
    }
}