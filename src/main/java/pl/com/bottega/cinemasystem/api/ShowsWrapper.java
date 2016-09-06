package pl.com.bottega.cinemasystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//todo decide polymorphism or logic
public class ShowsWrapper {

    private Long movieId;
    private CalendarDto calendar;
    private Collection<Date> dates;

    public List<Date> getShowDates() {
        return new ArrayList<>(); //TODO add logic
    }

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

    public Collection<Date> getDates() {
        return dates;
    }

    public void setDates(Collection<Date> dates) {
        this.dates = dates;
    }

    public void validate() {
    //todo validate
    }
}
