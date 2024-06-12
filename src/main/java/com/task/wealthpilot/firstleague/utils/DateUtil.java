package com.task.wealthpilot.firstleague.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String formatDate(LocalDate date) {
        return date.format(formatter);
    }
}
