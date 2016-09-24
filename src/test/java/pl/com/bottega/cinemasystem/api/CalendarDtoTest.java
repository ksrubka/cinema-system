package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class CalendarDtoTest {

    private CalendarDto calendarDto;
    private CalendarBasedValidationStrategy calendarBasedValidationStrategy;

    @Before
    public void setUp() {
        calendarDto = new CalendarDto();
    }

    @Test
    public void shouldValidate() {


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
