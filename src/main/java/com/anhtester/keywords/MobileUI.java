package com.anhtester.keywords;

import com.anhtester.drivers.DriverManager;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MobileUI {

    private static Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2);
    }

    @Step("TAP on element {0}")
    public static void tap(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverManager.getDriver().perform(Collections.singletonList(sequence));
    }

    @Step("TAP at point X={0}, Y={1}")
    public static void tap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(200))); //Chạm nhẹ nhanh
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(tap));
    }

    @Step("TAP and LONG PRESS at point X={0}, Y={1} with {2} second")
    public static void tap(int x, int y, int second) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(second))); //Chạm vào với thời gian chỉ định
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(tap));
    }

    @Step("Scroll to point X_start={0}, Y_start={1}, X_end={2}, Y_end={3}")
    public static void scrollWithGesturePlugin(int X_start, int Y_start, int X_end, int Y_end) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(X_start, Y_start);
        var end = new Point(X_end, Y_end);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(swipe));
    }

    @Step("Scroll Down with Gesture plugin")
    public static void scrollDownWithGesturePlugin() {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Collections.singletonList(sequence));
    }

    @Step("Scroll Down with Actions class of Selenium")
    public static void scrollDownWithActionsClass() {
        // Lấy kích thước màn hình
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = size.width / 2; // Giữa màn hình theo chiều ngang
        int startY = (int) (size.height * 0.8); // Gần cuối màn hình
        int endY = (int) (size.height * 0.2);  // Gần đầu màn hình

        new Actions(DriverManager.getDriver())
                .moveToLocation(startX, startY)
                .clickAndHold()
                .pause(500)
                .moveToLocation(startX, endY)
                .pause(500)
                .release()
                .perform();
        DriverManager.getDriver().resetInputState();
    }

    @Step("ZOOM with Gesture plugin")
    public static void zoom(WebElement element) {
        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

//        Sequence sequence = new Sequence(finger1, 1)
//                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
//                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
//                .addAction(new Pause(finger1, Duration.ofMillis(500)))
//                .addAction(finger1.createPointerMove(Duration.ofMillis(500),
//                        PointerInput.Origin.viewport(),
//                        centerOfElement.getX() + 200,
//                        centerOfElement.getY() - 100))
//                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(500)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        centerOfElement.getX() - 100,
                        centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //DriverManager.getDriver().perform(Arrays.asList(sequence, sequence2));
        DriverManager.getDriver().perform(Arrays.asList(sequence2));
    }

    @Step("Drag and Drop with Gesture plugin")
    public static void dragAndDrop(WebElement source, WebElement target) {
        Point sourceElementCenter = getCenterOfElement(source.getLocation(), source.getSize());
        Point targetElementCenter = getCenterOfElement(target.getLocation(), target.getSize());
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(1000)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), targetElementCenter))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Collections.singletonList(sequence));
    }

    @Step("Click on element {0}")
    public static void clickElement(WebElement element) {
        LogUtils.info("Click on element " + element);
        element.click();
    }

    @Step("Click on element {0}")
    public static void clickElement(By element) {
        LogUtils.info("Click on element " + element);
        DriverManager.getDriver().findElement(element).click();
    }

    @Step("Clear text on element {0}")
    public static void clearText(WebElement element) {
        LogUtils.info("Clear text on element " + element);
        element.clear();
    }

    @Step("Set text {1} on element {0}")
    public static void setText(WebElement element, String text) {
        LogUtils.info("Set text on element " + element);
        element.sendKeys(text);
    }

    @Step("Set text {1} on element {0}")
    public static void setText(By element, String text) {
        LogUtils.info("Set text on element " + element);
        DriverManager.getDriver().findElement(element).sendKeys(text);
    }

    @Step("Get text of element {0}")
    public static String getText(WebElement element) {
        LogUtils.info("Get text of element " + element);
        String text = element.getText();
        LogUtils.info("==> " + text);
        return text;
    }

    @Step("Check element present {0}")
    public static boolean isPresent(By by) {
        LogUtils.info("Check element present " + by);
        List<WebElement> check = DriverManager.getDriver().findElements(by);
        LogUtils.info("==> " + (check.size() > 0));
        return check.size() > 0;
    }

    @Step("Check element is displayed {0}")
    public static boolean isDisplayed(WebElement element) {
        LogUtils.info("Check element is displayed " + element);
        try {
            boolean check = element.isDisplayed();
            LogUtils.info("==> " + check);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify if two object are equal.
     *
     * @param actual   The object one
     * @param expected The object two
     * @return true/false
     */
    @Step("Verify Equals: {0} <=> {1}")
    public static boolean verifyEquals(Object actual, Object expected) {
        boolean result = actual.equals(expected);
        if (result) {
            LogUtils.info("Verify Equals: " + actual + " = " + expected);
            AllureManager.saveTextLog("Verify Equals: " + actual + " = " + expected);
        } else {
            LogUtils.error("❌ Verify Equals: " + actual + " != " + expected);
            AllureManager.saveTextLog("❌ Verify Equals: " + actual + " != " + expected);
            Assert.assertEquals(actual, expected, "❌ " + actual + " != " + expected);
        }
        return result;
    }

    /**
     * Verify if two object are equal.
     *
     * @param actual   The object one
     * @param expected The object two
     * @param message  The custom message if false
     * @return true/false
     */
    @Step("Verify Equals: {0} <=> {1}")
    public static boolean verifyEquals(Object actual, Object expected, String message) {
        boolean result = actual.equals(expected);
        if (result) {
            LogUtils.info("Verify Equals: " + actual + " = " + expected);
            AllureManager.saveTextLog("Verify Equals: " + actual + " = " + expected);
        } else {
            LogUtils.error("❌ Verify Equals: " + actual + " != " + expected);
            AllureManager.saveTextLog("❌ Verify Equals: " + actual + " != " + expected);
            Assert.assertEquals(actual, expected, "❌ " + message);
        }
        return result;
    }

    /**
     * Verify if the first object contains the second object.
     *
     * @param actual   The first object
     * @param expected The second object
     * @return true/false
     */
    @Step("Verify Contains: {0} <=> {1}")
    public static boolean verifyContains(String actual, String expected) {
        boolean result = actual.contains(expected);
        if (result) {
            LogUtils.info("Verify Equals: " + actual + " CONTAINS " + expected);
            AllureManager.saveTextLog("Verify Contains: " + actual + "CONTAINS" + expected);
        } else {
            LogUtils.error("❌ Verify Contains: " + actual + " NOT CONTAINS " + expected);
            AllureManager.saveTextLog("Verify Contains: " + actual + " NOT CONTAINS " + expected);

            Assert.assertEquals(actual, expected, "❌ " + actual + " NOT CONTAINS " + expected);
        }
        return result;
    }

    /**
     * Verify if the first object contains the second object.
     *
     * @param actual   The first object
     * @param expected The second object
     * @param message  The custom message if false
     * @return true/false
     */
    @Step("Verify Contains: {0} <=> {1}")
    public static boolean verifyContains(String actual, String expected, String message) {
        boolean result = actual.contains(expected);
        if (result) {
            LogUtils.info("Verify Contains: " + actual + " CONTAINS " + expected);
            AllureManager.saveTextLog("Verify Contains: " + actual + "CONTAINS" + expected);
        } else {
            LogUtils.error("❌ Verify Contains: " + actual + " NOT CONTAINS " + expected);
            AllureManager.saveTextLog("❌ Verify Contains: " + actual + " NOT CONTAINS " + expected);

            Assert.assertEquals(actual, expected, message);
        }
        return result;
    }

    /**
     * Verify the condition is true.
     *
     * @return true/false
     */
    @Step("Verify TRUE: {0}")
    public static boolean verifyTrue(boolean condition) {
        if (condition) {
            LogUtils.info("Verify TRUE: " + condition);
        } else {
            LogUtils.error("❌ Verify TRUE: " + condition);
            Assert.assertTrue(condition, "❌ The condition is FALSE.");
        }
        return condition;
    }

    /**
     * Verify the condition is true.
     *
     * @param message the custom message if false
     * @return true/false
     */
    @Step("Verify TRUE: {0}")
    public static boolean verifyTrue(boolean condition, String message) {
        if (condition) {
            LogUtils.info("Verify TRUE: " + condition);
        } else {
            LogUtils.error("❌ Verify TRUE: " + condition);
            Assert.assertTrue(condition, message);
        }
        return condition;
    }

}
