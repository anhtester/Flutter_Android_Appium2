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

public class ConfigPage extends BasePage {
    @AndroidFindBy(accessibility = "Server database")
    @iOSXCUITFindBy(accessibility = "Server database")
    public WebElement menuServerDatabase;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Data 1\")]/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//android.view.View[contains(@content-desc,\"Data 1\")]/android.widget.Button")
    public WebElement buttonDownloadData1;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Data 2\")]/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//android.view.View[contains(@content-desc,\"Data 2\")]/android.widget.Button")
    public WebElement buttonDownloadData2;

    @AndroidFindBy(accessibility = "Replace")
    @iOSXCUITFindBy(accessibility = "Replace")
    public WebElement buttonReplace;

    @AndroidFindBy(accessibility = "Downloaded")
    @iOSXCUITFindBy(accessibility = "Downloaded")
    public WebElement alertDownloaded;

    public ConfigPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public void importData(String dataName) {
        MobileUI.clickElement(menuConfig);
        MobileUI.clickElement(menuServerDatabase);
        MobileUI.clickElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,\"" + dataName + "\")]/android.widget.Button"));
        MobileUI.clickElement(buttonReplace);
        Assert.assertTrue(MobileUI.isDisplayed(alertDownloaded), "The alert message Downloaded not display.");
        MobileUI.clickElement(buttonBack);
        MobileUI.clickElement(menuMenu);
    }

}
