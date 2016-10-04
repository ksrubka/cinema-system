package pl.com.bottega.cinemasystem.api.responses;

import pl.com.bottega.cinemasystem.api.dtos.CinemaDto;

import java.util.Collection;

public class ListAllCinemasResponse {

    public Collection<CinemaDto> getCinemas() {
        return cinemas;
    }

    private Collection<CinemaDto> cinemas;

    public ListAllCinemasResponse(Collection<CinemaDto> cinemas) {
        this.cinemas = cinemas;
    }

}