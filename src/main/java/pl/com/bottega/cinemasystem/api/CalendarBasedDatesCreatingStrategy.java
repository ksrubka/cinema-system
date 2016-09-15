package pl.com.bottega.cinemasystem.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private CalendarDto calendar;
    private SimpleDateFormat dateAndHourFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    private SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm");

    public CalendarBasedDatesCreatingStrategy(CalendarDto calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate() {
        checkDates();
        checkHours();
        checkWeekdays();
    }

    private void checkDates() {
        check(dateAndHourFormatter, calendar.getFromDate(),
                "Incorrect start borderDate format: " + calendar.getFromDate());
        check(dateAndHourFormatter, calendar.getUntilDate(),
                "Incorrect end borderDate format: " + calendar.getUntilDate());
    }

    private void checkHours() {
        Collection<String> hours = calendar.getHours();
        hours.forEach(hour -> check(hourFormatter, hour, "Incorrect hour format: " + hour));
    }

    private void check(SimpleDateFormat formatter,
                       String formatToCheck, String errorMessage) {
        try {
            formatter.parse(formatToCheck);
        } catch (ParseException e) {
            throw new InvalidRequestException(errorMessage);
        }
    }

    private void checkWeekdays() {
        try {
            calendar.getWeekDays().forEach(day -> Weekday.valueOf(day));
        } catch (Exception ex) {
            throw new InvalidRequestException("Incorrect weekday");
        }
    }

    @Override
    public Set<Date> generateShowDates() {
        Date startDate = parseDate(calendar.getFromDate());
        Date endDate = parseDate(calendar.getUntilDate());
        Set<Weekday> weekdays = parseWeekdays();
        Set<Integer> weekdayCodes = generateWeekdayCodes(weekdays);

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);

        Set<Date> dates = new TreeSet<>();
        dates.addAll(prepareShowDatesForFirstDay(weekdayCodes, start));
        dates.addAll(prepareShowDatesForMiddleDays(weekdayCodes, start, end));
        dates.addAll(prepareShowDatesForLastDay(weekdayCodes, end));
        return dates;
    }

    private Date parseDate(String stringDate) {
        Date date;
        try {
            date = dateAndHourFormatter.parse(stringDate);
            setCorrectHour(stringDate, date);
        } catch (ParseException e) {
            throw new InvalidRequestException("Incorrect borderDate format: " + stringDate);
        }
        return date;
    }

    private void setCorrectHour(String stringDate, Date date) {
        Calendar validDate = Calendar.getInstance();
        validDate.setTime(date);
        validDate.set(Calendar.HOUR_OF_DAY, getHour(stringDate));
    }

    private int getHour(String stringDate) {
        String[] dateAndTime = stringDate.split(" ");
        String stringTime = dateAndTime[1];
        String[] hourAndMinute = stringTime.split(":");
        String hour = hourAndMinute[0];
        return Integer.valueOf(hour);
    }

    private Set<Weekday> parseWeekdays() {
        return calendar.getWeekDays().stream()
                .map(day -> Weekday.valueOf(day))
                .collect(Collectors.toSet());
    }

    private Set<Integer> generateWeekdayCodes(Set<Weekday> weekdays) {
        return weekdays.stream()
                .map(weekday -> weekday.getOrder())
                .collect(Collectors.toSet());
    }

    private Set<Date> prepareShowDatesForFirstDay(Set<Integer> weekdayCodes, Calendar startDate) {
        Set<Date> showDates = new TreeSet<>();
        if (currentDayIsShowDay(weekdayCodes, startDate) &&
                !isMidnight(startDate)) {
            int[][] showHours = prepareShowHours();
            Calendar tempShowHour = (Calendar) startDate.clone();
            for (int[] showHour : showHours) {
                setHour(tempShowHour, showHour[0], showHour[1]);
                if (tempShowHour.after(startDate) || tempShowHour.equals(startDate)) {
                    showDates.add(tempShowHour.getTime());
                }
            }
        }
        return showDates;
    }

    private boolean currentDayIsShowDay(Set<Integer> weekdayCodes, Calendar date) {
        int currentDayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        return weekdayCodes.contains(currentDayOfWeek);
    }

    private boolean isMidnight(Calendar date) {
        Calendar zeroTestDate = (Calendar) date.clone();
        setHour(zeroTestDate, 0, 0);
        return date.compareTo(zeroTestDate) == 0;
    }

    //hours format:
    // {{12,43}, {15, 0}} -> is a list of two hours -> 12:43 and 15:00
    private int[][] prepareShowHours() {
        int[][] hours = new int[calendar.getHours().size()][2];
        int index = 0;
        for (String time : calendar.getHours()) {
            hours[index] = parseHourAndMinute(time);
            index++;
        }
        return hours;
    }

    private int[] parseHourAndMinute(String time) {
        String[] stringHourAndMinute = time.split(":");
        int[] hourAndMinute = new int[2];
        hourAndMinute[0] = Integer.valueOf(stringHourAndMinute[0]);
        hourAndMinute[1] = Integer.valueOf(stringHourAndMinute[1]);
        return hourAndMinute;
    }

    private void setHour(Calendar beginningOfLastDay, int hour, int minute) {
        beginningOfLastDay.set(Calendar.HOUR_OF_DAY, hour);
        beginningOfLastDay.set(Calendar.MINUTE, minute);
    }

    private Set<Date> prepareShowDatesForMiddleDays(Set<Integer> weekdayCodes, Calendar startDate, Calendar endDate) {
        Calendar endOfFirstDay = prepareBeginningOfMiddleDays(startDate);
        Calendar beginningOfLastDay = prepareEndOfMiddleDays(endDate);
        Set<Date> showDates = new TreeSet<>();
        while (!endOfFirstDay.after(beginningOfLastDay)) {
            Set<Date> dates = prepareShowsForOneDay(weekdayCodes, endOfFirstDay);
            showDates.addAll(dates);
            addOneDay(endOfFirstDay);
        }
        return showDates;
    }

    private Calendar prepareBeginningOfMiddleDays(Calendar startDate) {
        Calendar endOfFirstDay;
        if (!(isMidnight(startDate))) {
            endOfFirstDay = prepareEndOfFirstDay(startDate);
        } else {
            endOfFirstDay = startDate;
        }
        return endOfFirstDay;
    }

    private Calendar prepareEndOfFirstDay(Calendar startCalendar) {
        Calendar endOfFirstDay = (Calendar) startCalendar.clone();
        addOneDay(endOfFirstDay);
        setHour(endOfFirstDay, 0, 0);
        return endOfFirstDay;
    }

    private Calendar prepareEndOfMiddleDays(Calendar endDate) {
        Calendar zeroTestDate = (Calendar) endDate.clone();
        setHour(zeroTestDate, 0, 0);
        Calendar beginningOfLastDay;
        if (!(isMidnight(endDate))) {
            beginningOfLastDay = zeroTestDate;
        } else {
            beginningOfLastDay = endDate;
        }
        return beginningOfLastDay;
    }

    private void addOneDay(Calendar endOfFirstDay) {
        endOfFirstDay.add(Calendar.DATE, 1);
    }

    private Set<Date> prepareShowsForOneDay(Set<Integer> weekdayCodes, Calendar startDate) {
        Set<Date> showDates = new TreeSet<>();
        if (currentDayIsShowDay(weekdayCodes, startDate)) {
            int[][] showHours = prepareShowHours();
            Calendar tempShowHour = (Calendar) startDate.clone();
            for (int[] showHour : showHours) {
                setHour(tempShowHour, showHour[0], showHour[1]);
                showDates.add(tempShowHour.getTime());
            }
        }
        return showDates;
    }

    private Set<Date> prepareShowDatesForLastDay(Set<Integer> weekdayCodes, Calendar endDate) {
        Set<Date> showDates = new TreeSet<>();
        if (currentDayIsShowDay(weekdayCodes, endDate) &&
                !isMidnight(endDate)) {
            int[][] showHours = prepareShowHours();
            Calendar tempShowHour = (Calendar) endDate.clone();
            for (int[] showHour : showHours) {
                setHour(tempShowHour, showHour[0], showHour[1]);
                if (tempShowHour.before(endDate) || tempShowHour.equals(endDate)) {
                    showDates.add(tempShowHour.getTime());
                }
            }
        }
        return showDates;
    }
}
