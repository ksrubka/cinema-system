package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;


public class ShowDto {
    Long id;
    @JsonFormat (pattern = "YYYY/MM/dd")
    LocalTime time;
}
