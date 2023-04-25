package org.hainu.onlinedrugstore.utils;

import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author longbh
 * @description 日期工具类
 * @date 2023/2/25 下午3:46
 */
public class DateUtil {

    private DateUtil(){

    }

    public static Date parseToDate(String date, String style) {
        DateFormat format = new SimpleDateFormat(style);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formartDate(Date date, String style) {
        DateFormat format = new SimpleDateFormat(style);
        return format.format(date);
    }

    public static String formartDate(Long date, String style) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(style);
        return format.format(new Date(date));
    }

    public static Date addToDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Long addToMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return calendar.getTimeInMillis();
    }

    public static Date addToDay0(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date addToDay59(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date parseToDay0(String date, String style) {
        DateFormat format = new SimpleDateFormat(style);
        try {
            Date dDate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 1);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseToDay59(String date, String style) {
        DateFormat format = new SimpleDateFormat(style);
        try {
            Date dDate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dDate);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> formatHours() {
        List<String> hours = Lists.newArrayList();
        hours.add("00:00");
        hours.add("01:00");
        hours.add("02:00");
        hours.add("03:00");
        hours.add("04:00");
        hours.add("05:00");
        hours.add("06:00");
        hours.add("07:00");
        hours.add("08:00");
        hours.add("09:00");
        hours.add("10:00");
        hours.add("11:00");
        hours.add("12:00");
        hours.add("13:00");
        hours.add("14:00");
        hours.add("15:00");
        hours.add("16:00");
        hours.add("17:00");
        hours.add("18:00");
        hours.add("19:00");
        hours.add("20:00");
        hours.add("21:00");
        hours.add("22:00");
        hours.add("23:00");
        return hours;
    }

    public static List<String> formatDays(Date start, Date end) {
        List<String> days = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (calendar.getTimeInMillis() < end.getTime()) {
            days.add(formartDate(calendar.getTime(), "yyyy-MM-dd"));
            calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

}
