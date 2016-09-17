package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static java.time.Year.isLeap;

public class DateUtil {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    private static final int FAR_FUTURE_YEAR = 2030;

    public static void validate(String stringDate) {
        checkFormat(stringDate);
        checkDate(stringDate);
        checkHourAndMinuteInDate(stringDate);
    }

    public static void checkFormat(String stringDate) {
        try {
            formatter.parse(stringDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Incorrect stringDate format");
        }
    }

    private static void checkDate(String stringDate) {
        String[] yearMonthDay = generateDate(stringDate);
        int year = Integer.valueOf(yearMonthDay[0]);
        int month = Integer.valueOf(yearMonthDay[1]);
        int day = Integer.valueOf(yearMonthDay[2]);
        checkDateNumbers(stringDate, year, month, day);
    }

    private static void checkDateNumbers(String stringDate, int year, int month, int day) {
        if (year > FAR_FUTURE_YEAR || year < 0) {
            throw new InvalidRequestException("Incorrect year: " + year );
        }
        if (month > 12 || month <= 0) {
            throw new InvalidRequestException("Incorrect month: " + month + " in date: " + stringDate);
        }
        if (day > 31 || day <= 0) {
            incorrectDayException(stringDate, day);
        }
        if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            throw new InvalidRequestException("Date does not exist: " + stringDate);
        }
        if (month == 2 && (day > 29)) {
            incorrectDayException(stringDate, day);
        }
        if (month == 2 && (day == 29 && !isLeap(year))) {
            incorrectDayException(stringDate, day);
        }
    }

    private static void incorrectDayException(String stringDate, int day) {
        throw new InvalidRequestException("Incorrect day: " + day + " in date: " + stringDate);
    }

    private static String[] generateDate(String stringDate) {
        String[] dateAndTime = stringDate.split(" ");
        String date = dateAndTime[0];
        String[] yearMonthDay = date.split("/");
        return yearMonthDay;
    }

    private static void checkHourAndMinuteInDate(String stringDate) {
        int[] hourAndMinute = parseDateToHourAndMinute(stringDate);
        int hour = hourAndMinute[0];
        int minute = hourAndMinute[1];
        if (hour <= 0 || hour > 23) {
            throw new InvalidRequestException("Incorrect hour: " + hour + " in date: " + stringDate);
        }
        if (minute < 0 || minute > 59) {
            throw new InvalidRequestException("Incorrect minute: " + minute + " in date: " + stringDate);
        }
    }

    public static void checkHourAndMinuteInTime(String time) {
        int[] hourAndMinute = prepareHourAndMinute(time);
        int hour = hourAndMinute[0];
        int minute = hourAndMinute[1];
        if (hour <= 0 || hour > 23) {
            throw new InvalidRequestException("Incorrect hour: " + hour + " in: " + time);
        }
        if (minute < 0 || minute > 59) {
            throw new InvalidRequestException("Incorrect minute: " + minute + " in: " + time);
        }
    }

    public static int[] prepareHourAndMinute(String time) {
        String[] stringHourAndMinute = time.split(":");
        int[] hourAndMinute = new int[2];
        hourAndMinute[0] = Integer.valueOf(stringHourAndMinute[0]);
        hourAndMinute[1] = Integer.valueOf(stringHourAndMinute[1]);
        return hourAndMinute;
    }

    public static int[] parseDateToHourAndMinute(String date) {
        String time = prepareTime(date);
        return prepareHourAndMinute(time);
    }

    public static String prepareTime(String date) {
        String[] dateAndTime = date.split(" ");
        return dateAndTime[1];
    }

    public static Date parseDate(String stringDate) {
        Date date;
        try {
            date = formatter.parse(stringDate);
            date = setCorrectHour(stringDate, date);
        } catch (ParseException e) {
            throw new InvalidRequestException("Incorrect stringDate format");
        }
        return date;
    }

    private static Date setCorrectHour(String stringDate, Date date) {
        Calendar validDate = Calendar.getInstance();
        validDate.setTime(date);
        validDate.set(Calendar.HOUR_OF_DAY, getHour(stringDate));
        return validDate.getTime();
    }

    private static int getHour(String stringDate) {
        String[] dateAndTime = stringDate.split(" ");
        String stringTime = dateAndTime[1];
        String[] hourAndMinute = stringTime.split(":");
        String hour = hourAndMinute[0];
        return Integer.valueOf(hour);
    }
}
