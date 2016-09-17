package pl.com.bottega.cinemasystem.api;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.Collection;

public class ShowsDto {

    private Long movieId;
    private CalendarDto calendar;
    @ElementCollection(fetch = FetchType.EAGER)
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