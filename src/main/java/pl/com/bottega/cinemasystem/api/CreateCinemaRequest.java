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
         if (this.name == null || this.name.trim().isEmpty())
             throw new InvalidRequestException("The cinema's name is required");
         if (this.city == null || this.city.trim().isEmpty())
             throw new InvalidRequestException("The city is required");

         if (cinemaRepository.load(this.name, this.city)!= null)
             throw new InvalidRequestException("This cinema exists in the system");
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
