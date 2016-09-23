package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import static java.time.Year.isLeap;

public class DateUtil {

    public static void validateDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        validateDateNumbers(date, year, month, day);
    }

    private static void validateDateNumbers(LocalDate date, int year, int month, int day) {
        if (year < 0) {
            throw new InvalidRequestException("Incorrect year: " + year);
        }
        if (month > 12 || month <= 0) {
            throw new InvalidRequestException("Incorrect month: " + month + " in date: " + date);
        }
        if (day > 31 || day <= 0) {
            incorrectDayException(date, day);
        }
        if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            throw new InvalidRequestException("Date does not exist: " + date);
        }
        if (month == 2 && (day > 29)) {
            incorrectDayException(date, day);
        }
        if (month == 2 && (day == 29 && !isLeap(year))) {
            incorrectDayException(date, day);
        }
    }

    private static void incorrectDayException(LocalDate date, int day) {
        throw new InvalidRequestException("Incorrect day: " + day + " in date: " + date);
    }

    public static void validateTime(LocalTime date) {
        int hour = date.getHour();
        int minute = date.getMinute();
        if (hour <= 0 || hour > 23) {
            throw new InvalidRequestException("Incorrect hour: " + hour + " in date: " + date);
        }
        if (minute < 0 || minute > 59) {
            throw new InvalidRequestException("Incorrect minute: " + minute + " in date: " + date);
        }
    }

    public static void checkIfDatesHasNotPassed(Collection<LocalDateTime> dates) {
        dates.forEach(date -> checkIfDatePassed(date));
    }

    private static void checkIfDatePassed(LocalDateTime date) {
        if (dateHasPassed(date)) {
            throw new InvalidRequestException("Given date has already passed: " + date);
        }
    }

    private static boolean dateHasPassed(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        return now.compareTo(date) > 0;
    }
}
