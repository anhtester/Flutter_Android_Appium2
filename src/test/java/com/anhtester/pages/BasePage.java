package com.anhtester.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.MobileUI;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @AndroidFindBy(accessibility = "Back")
    @iOSXCUITFindBy(accessibility = "Back")
    public WebElement buttonBack;

    @AndroidFindBy(accessibility = "Cancel")
    @iOSXCUITFindBy(accessibility = "Cancel")
    public WebElement buttonCancel;

    @AndroidFindBy(accessibility = "Date")
    @iOSXCUITFindBy(accessibility = "Date")
    public WebElement menuDate;

    @AndroidFindBy(accessibility = "Wallet")
    @iOSXCUITFindBy(accessibility = "Wallet")
    public WebElement menuWallet;

    @AndroidFindBy(accessibility = "Menu")
    @iOSXCUITFindBy(accessibility = "Menu")
    public WebElement menuMenu;

    @AndroidFindBy(accessibility = "Profile")
    @iOSXCUITFindBy(accessibility = "Profile")
    public WebElement menuProfile;

    @AndroidFindBy(accessibility = "Config")
    @iOSXCUITFindBy(accessibility = "Config")
    public WebElement menuConfig;

}
