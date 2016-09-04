package pl.com.bottega.cinemasystem.api;

public class CinemaDto {

    private String name;
    private String city;

    public void validate() {
        checkState();
    }

    private void checkState() {
        checkName();
        checkCity();
    }

    private void checkName() {
        if (this.name == null || this.name.trim().isEmpty())
            throw new InvalidRequestException("cinema name is required");
    }

    private void checkCity() {
        if (this.city == null || this.city.trim().isEmpty())
            throw new InvalidRequestException("cinema city location is required");
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
