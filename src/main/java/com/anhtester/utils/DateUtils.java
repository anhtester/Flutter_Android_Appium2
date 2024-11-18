/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package com.phamiliartech.utils;

import com.anhtester.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//final -> We do not want any class to extend this class
public final class DateUtils {

    private DateUtils() {
        super();
    }

    /**
     * @return lấy ra ngày tháng năm hiện tại của máy theo cấu trúc mặc định
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }

    /**
     * @return Get the current date, month, year, hour, minute, second of the device according to the structure dd/MM/yyyy HH:mm:ss
     */
    public static String getCurrentDateTime() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(now);
    }

    public static String getCurrentDateTimeCustom(String separator_Character) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeStamp = formatter.format(now).replace("/", separator_Character);
        timeStamp = timeStamp.replace(" ", separator_Character);
        timeStamp = timeStamp.replace(":", separator_Character);
        return timeStamp;
    }

    public static String dateFormatConverter(String dateString, String formatDateExpected){
        // Parse the original date string into a LocalDate object
        LocalDate date = LocalDate.parse(dateString);

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDateExpected);

        // Convert the LocalDate to the desired format
        String formattedDate = date.format(formatter);

        // Output the formatted date
        LogUtils.info(formattedDate);

        return formattedDate;
    }

}
