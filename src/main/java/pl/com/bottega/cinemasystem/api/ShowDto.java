package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public class ShowDto {
    Long id;
    @JsonFormat (pattern = "YYYY/MM/dd")
    LocalTime time;
}
