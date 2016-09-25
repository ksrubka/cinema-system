package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

public class CalendarDtoTest {

    private CalendarDto calendarDto;

    private CalendarBasedValidationStrategy calendarBasedValidationStrategy;

    private LocalDate fromDate = LocalDate.of(2017, 05, 20);
    private LocalDate untilDate = LocalDate.of(2017, 06, 20);
    private Collection<DayOfWeek> weekDays = new ArrayList<DayOfWeek>() {{
        add(DayOfWeek.MONDAY);
        add(DayOfWeek.TUESDAY);
        add(DayOfWeek.WEDNESDAY);
    }};
    private Collection<LocalTime> hours = new ArrayList<LocalTime>() {{
        add(LocalTime.of(18, 0));
        add(LocalTime.of(20, 0));
        add(LocalTime.of(22, 0));
    }};

    @Before
    public void setUp() {
        calendarDto = new CalendarDto();
        calendarBasedValidationStrategy = new CalendarBasedValidationStrategy(calendarDto);
    }

    @Test
    public void shouldValidate() {
        calendarDto = newCalendarDtoInstance(fromDate, untilDate, weekDays, hours);

        calendarBasedValidationStrategy.validate();
    }



    private CalendarDto newCalendarDtoInstance(LocalDate fromDate, LocalDate untilDate,
                                            Collection<DayOfWeek> weekDays, Collection<LocalTime> hours) {
        calendarDto.setFromDate(fromDate);
        calendarDto.setUntilDate(untilDate);
        calendarDto.setWeekDays(weekDays);
        calendarDto.setHours(hours);
        return calendarDto;

    }
}
