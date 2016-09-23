package pl.com.bottega.cinemasystem.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CalendarBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private CalendarDto calendar;
    private Set<Integer> weekdayCodes;

    public CalendarBasedDatesCreatingStrategy(CalendarDto calendar) {
        this.calendar = calendar;
        weekdayCodes = generateWeekdayCodes();
    }

    private Set<Integer> generateWeekdayCodes() {
        return calendar.getWeekDays().stream()
                .map(weekday -> weekday.getValue())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<LocalDateTime> generateShowDates() {
        LocalDate startDate = calendar.getFromDate();
        LocalDate endDate = calendar.getUntilDate();

        Set<LocalDateTime> dates = new TreeSet<>();
        LocalDateTime currentShowDate;
        while (startDate.isBefore(endDate.plusDays(1L))) {
            if (currentDayIsShowDay(startDate)) {
                for (LocalTime time : calendar.getHours()) {
                    currentShowDate = startDate.atTime(time);
                    dates.add(currentShowDate);
                }
            }
            startDate = startDate.plusDays(1L);
        }
        return dates;
    }

    private boolean currentDayIsShowDay(LocalDate date) {
        int currentDayOfWeek = date.getDayOfWeek().getValue();
        return weekdayCodes.contains(currentDayOfWeek);
    }
}
