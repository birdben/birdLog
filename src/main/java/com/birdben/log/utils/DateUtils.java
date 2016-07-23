package com.birdben.log.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author birdben
 * @version V1.0
 * @name: DateUtils
 * @description 日期工具类
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class DateUtils {

    public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date getDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);

        return calendar.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return calendar.getTime();
    }

    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date == null) return "";

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parse(String date) {
        return parse(date, DEFAULT_PATTERN);
    }

    public static Date parse(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}
