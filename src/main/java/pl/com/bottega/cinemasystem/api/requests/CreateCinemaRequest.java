package pl.com.bottega.cinemasystem.api.requests;

import pl.com.bottega.cinemasystem.api.dtos.CinemaDto;
import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

public class CreateCinemaRequest {

    private CinemaDto cinema;

    public void validate() {
        validateCity();
        validateName();
    }

    private void validateName() {
        ValidationUtils.validateString(getName(), "cinema name is required");
    }

    private void validateCity() {
        ValidationUtils.validateString(getCity(), "cinema city location is required");
    }


    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }
}
