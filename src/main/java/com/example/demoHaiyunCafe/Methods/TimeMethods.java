package com.example.demoHaiyunCafe.Methods;

import java.time.LocalDate;
import java.util.Calendar;

public class TimeMethods {
    private static CharSequence[] charSequences = {"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static long getWeek() {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth()); // 月份从0开始，年份和日期从1开始
        //myCalendar.set(2020,1-0,1);
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }


    public static int getWeekday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static CharSequence getChineseWeekday() {
        return charSequences[getWeekday()];
    }
}
