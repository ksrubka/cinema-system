package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.bottega.cinemasystem.domain.Show;

import java.time.LocalTime;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public class ShowDto {
    private Long id;
    @JsonFormat (pattern = "HH:mm")
    private LocalTime time;

    public ShowDto(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public ShowDto(Show show) {
        this.id = show.getId();
        this.time = show.getDate().toLocalTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
