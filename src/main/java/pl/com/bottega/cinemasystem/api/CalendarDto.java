package pl.com.bottega.cinemasystem.api;

import java.util.Collection;
import java.util.Date;

public class CalendarDto {

    private Date fromDate;
    private Date untilDate;
    private Collection<Weekday> weekDays;
    private Collection<String> hours;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Collection<Weekday> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Collection<Weekday> weekDays) {
        this.weekDays = weekDays;
    }

    public Collection<String> getHours() {
        return hours;
    }

    public void setHours(Collection<String> hours) {
        this.hours = hours;
    }
}
