package com.anhtester.constants;

import com.anhtester.helpers.PropertiesHelpers;
import com.anhtester.helpers.SystemHelpers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigData {

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH");
    public static final String JSON_DATA_FILE_PATH = PropertiesHelpers.getValue("JSON_DATA_FILE_PATH");
    public static final String JSON_CONFIG_FILE_PATH = PropertiesHelpers.getValue("JSON_CONFIG_FILE_PATH");
    public static final String TESTDATA_FOLDER_PATH = PropertiesHelpers.getValue("TESTDATA_FOLDER_PATH");
    public static final String LOCATE = PropertiesHelpers.getValue("LOCATE");

    public static final String AppiumDriverLocalService = PropertiesHelpers.getValue("AppiumDriverLocalService");

    //Set appium server
    public static final String IP_ADDRESS = PropertiesHelpers.getValue("IP_ADDRESS");
    public static final String PORT = PropertiesHelpers.getValue("PORT");
    public static final String TIMEOUT_SERVICE = PropertiesHelpers.getValue("TIMEOUT_SERVICE");

    //Set platform (android/ios) and device emulator (pixel7, pixel8,...) to get data from config.json
    public static final String PLATFORM = PropertiesHelpers.getValue("PLATFORM");
    public static final String DEVICE = PropertiesHelpers.getValue("DEVICE");

    public static String getValueJsonConfig(String platform, String device, String propertyName) {
        // Initialize Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Read JSON file
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(new File(ConfigData.JSON_CONFIG_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Navigate to the "automationName" field
        String result = rootNode
                .path("platforms")
                .path(platform)
                .path("devices")
                .path(device)
                .path(propertyName)
                .asText();

        System.out.println("***" + propertyName + ": " + result);
        return result;
    }

    public static String getValueJsonConfig(String propertyName) {
        // Initialize Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Read JSON file
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(new File(ConfigData.JSON_CONFIG_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Navigate to the "automationName" field
        String result = rootNode
                .path("platforms")
                .path(PLATFORM)
                .path("devices")
                .path(DEVICE)
                .path(propertyName)
                .asText();

        System.out.println("***" + propertyName + ": " + result);
        return result;
    }

}
