package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Collection;

public class ShowsDto {

    private Long cinemaId;
    private Long movieId;
    private CalendarDto calendar;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Collection<LocalDateTime> dates;

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

    public Collection<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(Collection<LocalDateTime> dates) {
        this.dates = dates;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }
}