package com.anhtester.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.MobileUI;
import com.anhtester.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test
    public void loginSuccess() {
        loginPage = new LoginPage();
        loginPage.login("admin", "admin");
    }
}
