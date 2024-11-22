package com.anhtester.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.MobileUI;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Mobile App Flutter Beta\"]/following-sibling::android.widget.EditText)[1]")
    @iOSXCUITFindBy(xpath = "(//android.view.View[@content-desc=\"Mobile App Flutter Beta\"]/following-sibling::android.widget.EditText)[1]")
    public WebElement inputEmail;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Mobile App Flutter Beta\"]/following-sibling::android.widget.EditText)[2]")
    @iOSXCUITFindBy(xpath = "(//android.view.View[@content-desc=\"Mobile App Flutter Beta\"]/following-sibling::android.widget.EditText)[2]")
    public WebElement inputPassword;

    @AndroidFindBy(accessibility = "Sign in")
    @iOSXCUITFindBy(accessibility = "Sign in")
    public WebElement buttonSignin;

//    @AndroidFindBy(accessibility = "")
//    @iOSXCUITFindBy(accessibility = "")
//    public WebElement inputEmail;
//
//    @AndroidFindBy(accessibility = "")
//    @iOSXCUITFindBy(accessibility = "")
//    public WebElement inputPassword;

    public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public void login(String email, String password) {
        MobileUI.clickElement(inputEmail);
        MobileUI.clearText(inputEmail);
        MobileUI.setText(inputEmail, email);

        MobileUI.clickElement(inputPassword);
        MobileUI.clearText(inputPassword);
        MobileUI.setText(inputPassword, password);

        MobileUI.clickElement(buttonSignin);

        Assert.assertTrue(MobileUI.isPresent(AppiumBy.accessibilityId("Menu")));
    }

    public void login() {
        MobileUI.clickElement(inputEmail);
        MobileUI.clearText(inputEmail);
        MobileUI.setText(inputEmail, "admin");

        MobileUI.clickElement(inputPassword);
        MobileUI.clearText(inputPassword);
        MobileUI.setText(inputPassword, "admin");

        MobileUI.clickElement(buttonSignin);

        Assert.assertTrue(MobileUI.isPresent(AppiumBy.accessibilityId("Menu")));
    }

}
