package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.CinemaRepository;

public class CreateCinemaRequest {

    private String name;
    private String city;


    public CreateCinemaRequest(String name, String city) {
        this.name = name;
        this.city = city;
    }


     public void validate (CinemaRepository cinemaRepository){

     }
}
