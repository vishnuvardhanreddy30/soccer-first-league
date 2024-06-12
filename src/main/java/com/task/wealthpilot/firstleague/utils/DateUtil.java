package com.task.wealthpilot.firstleague.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String formatDate(LocalDate date) {
        return date.format(formatter);
    }

    public static LocalDate getNextSaturday(String leagueStartDate) {
        return LocalDate.parse(leagueStartDate).with(DayOfWeek.SATURDAY);
    }

    public static LocalDate addWeeks(LocalDate date, int weeks) {
        return date.plusWeeks(weeks);
    }
}
