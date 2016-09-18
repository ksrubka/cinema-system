package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.util.*;
import java.util.stream.Collectors;

public class CalendarBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private CalendarDto calendar;

    public CalendarBasedDatesCreatingStrategy(CalendarDto calendar) {
        this.calendar = calendar;
    }

    @Override
    public Set<Date> generateShowDates() {
        Date startDate = DateUtil.parseDate(calendar.getFromDate());
        Date endDate = DateUtil.parseDate(calendar.getUntilDate());
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

    private Set<Weekday> parseWeekdays() {
        Collection<String> weekDays = calendar.getWeekDays();
        return weekDays.stream()
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
            Calendar currentShowHour = (Calendar) startDate.clone();
            for (int[] showHour : showHours) {
                setHour(currentShowHour, hour(showHour), minute(showHour));
                if (currentShowHour.after(startDate) || currentShowHour.equals(startDate)) {
                    showDates.add(currentShowHour.getTime());
                }
            }
        }
        return showDates;
    }

    private int hour(int[] showHour) {
        return showHour[0];
    }

    private int minute(int[] showHour) {
        return showHour[1];
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
        Collection<String> calendarHours = calendar.getHours();
        int[][] hours = new int[calendarHours.size()][2];
        int index = 0;
        for (String time : calendarHours) {
            hours[index] = DateUtil.prepareHourAndMinute(time);
            index++;
        }
        return hours;
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
            Calendar currentShowHour = (Calendar) startDate.clone();
            for (int[] showHour : showHours) {
                setHour(currentShowHour, hour(showHour), minute(showHour));
                showDates.add(currentShowHour.getTime());
            }
        }
        return showDates;
    }

    private Set<Date> prepareShowDatesForLastDay(Set<Integer> weekdayCodes, Calendar endDate) {
        Set<Date> showDates = new TreeSet<>();
        if (currentDayIsShowDay(weekdayCodes, endDate) &&
                !isMidnight(endDate)) {
            int[][] showHours = prepareShowHours();
            Calendar currentShowHour = (Calendar) endDate.clone();
            for (int[] showHour : showHours) {
                setHour(currentShowHour, hour(showHour), minute(showHour));
                if (currentShowHour.before(endDate) || currentShowHour.equals(endDate)) {
                    showDates.add(currentShowHour.getTime());
                }
            }
        }
        return showDates;
    }
}
