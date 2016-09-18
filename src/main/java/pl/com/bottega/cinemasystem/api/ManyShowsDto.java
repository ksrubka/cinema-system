package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collection;

public class ManyShowsDto {

    private Long movieId;
    private CalendarDto calendar;

    /*@JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Collection<Date> dates;*/

    private Collection<String> dates;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public CalendarDto getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarDto calendar) {
        this.calendar = calendar;
    }

    public Collection<String> getDates() {
        return dates;
    }

    public void setDates(Collection<String> dates) {
        this.dates = dates;
    }
}