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
         if (cinemaRepository.load(this.name, this.city)!= null)
             throw new InvalidRequestException();
         if (this.name.equals("")|| this.city.isEmpty())
             throw new InvalidRequestException();
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
