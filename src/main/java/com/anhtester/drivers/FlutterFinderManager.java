package com.anhtester.drivers;

import io.github.ashwith.flutter.FlutterElement;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public class FlutterFinderManager {

    public static WebElement byValueKey(String valueKey) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byValueKey(valueKey);
        return element;
    }

    public static WebElement byValueKey(int valueKey) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byValueKey(valueKey);
        return element;
    }

    public static WebElement byToolTip(String toolTipText) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byToolTip(toolTipText);
        return element;
    }

    public static WebElement byType(String type) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byType(type);
        return element;
    }

    public static WebElement byText(String text) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byText(text);
        return element;
    }

    public static WebElement bySemanticsLabel(String label) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.bySemanticsLabel(label);
        return element;
    }

    public static WebElement bySemanticsLabel(Pattern label) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.bySemanticsLabel(label);
        return element;
    }

    public static WebElement byAncestor(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byAncestor(of, matching, matchRoot, firstMatchOnly);
        return element;
    }

    public static WebElement byDescendant(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        FlutterFinder finder = new FlutterFinder(DriverManager.getDriver());
        WebElement element = finder.byDescendant(of, matching, matchRoot, firstMatchOnly);
        return element;
    }

}
