package com.saurett.cjm.utils;

import java.util.Calendar;

/**
 * Created by jvier on 04/09/2017.
 */

public class DateTimeUtils {

    public static long  getTimeStamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
}
