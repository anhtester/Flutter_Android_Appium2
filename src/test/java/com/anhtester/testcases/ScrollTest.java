package com.anhtester.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.MobileUI;
import com.anhtester.pages.ConfigPage;
import com.anhtester.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ScrollTest extends BaseTest {

    @Test
    public void testScrollOnFlutterApp() {
        new LoginPage().login();
        new ConfigPage().importData("Data 1");

        //MobileUI.scroll(776, 1659, 776, 992);

        WebElement element = DriverManager.getDriver().findElement(AppiumBy.accessibilityId("Table VIP"));
        System.out.println("***Table VIP: " + element);

        // Sử dụng scrollGesture để cuộn tới phần tử
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("elementId", ((RemoteWebElement) element).getId()); // ID phần tử
//        params.put("direction", "down"); // Hướng cuộn: "up", "down", "left", "right"
//        params.put("percent", 0.75); // Độ dài cuộn (75% màn hình)
//        DriverManager.getDriver().executeScript("mobile: scrollGesture", params);

        // Sử dụng swipeGesture để vuốt màn hình đến phần tử
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("elementId", ((RemoteWebElement) element).getId()); // ID phần tử
//        params.put("direction", "down"); // Hướng vuốt: "up", "down", "left", "right"
//        params.put("percent", 0.75); // Độ dài vuốt (75% màn hình)
//        DriverManager.getDriver().executeScript("mobile: swipeGesture", params);

        // Lấy kích thước màn hình
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = size.width / 2; // Giữa màn hình theo chiều ngang
        int startY = (int) (size.height * 0.8); // Gần cuối màn hình
        int endY = (int) (size.height * 0.2);  // Gần đầu màn hình

        // Tạo lệnh swipeGesture với tọa độ
//        HashMap<String, Object> swipeParams = new HashMap<>();
//        swipeParams.put("startX", startX); // Tọa độ bắt đầu
//        swipeParams.put("startY", startY);
//        swipeParams.put("endX", startX);   // Tọa độ kết thúc
//        swipeParams.put("endY", endY);
//        swipeParams.put("elementId", ((RemoteWebElement) element).getId()); // ID phần tử
//        swipeParams.put("duration", 1000); // Thời gian vuốt (ms)
//        swipeParams.put("direction", "up"); // Hướng vuốt, bắt buộc: "up", "down", "left", "right"
//        swipeParams.put("percent", 0.9);    // Di chuyển 75% của màn hình
//        DriverManager.getDriver().executeScript("mobile: swipeGesture", swipeParams);

        MobileUI.scrollDownWithActionsClass();
        MobileUI.zoom(element);

        MobileUI.sleep(2);
    }
}
