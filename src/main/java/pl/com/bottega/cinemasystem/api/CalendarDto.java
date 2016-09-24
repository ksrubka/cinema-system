package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class CalendarDto {

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fromDate, untilDate;

    private Collection<DayOfWeek> weekDays;

    @JsonFormat(pattern = "HH:mm")
    private Collection<LocalTime> hours;

    public CalendarDto() {
    }

    public CalendarDto(LocalDate fromDate, LocalDate untilDate,
                       Collection<DayOfWeek> weekDays, Collection<LocalTime> hours) {
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.weekDays = weekDays;
        this.hours = hours;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDate untilDate) {
        this.untilDate = untilDate;
    }

    public Collection<DayOfWeek> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Collection<DayOfWeek> weekDays) {
        this.weekDays = weekDays;
    }

    public Collection<LocalTime> getHours() {
        return hours;
    }

    public void setHours(Collection<LocalTime> hours) {
        this.hours = hours;
    }
}
