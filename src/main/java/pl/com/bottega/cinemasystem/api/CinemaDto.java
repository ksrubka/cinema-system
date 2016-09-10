package pl.com.bottega.cinemasystem.api;

public class CinemaDto {

    private String name;
    private String city;

    public CinemaDto(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void validate() {
        checkState();
    }

    private void checkState() {
        checkName();
        checkCity();
        checkState();
        CheckNameLength();
        checkCityLength();
        checkIsExist();
    }

    private void checkCityLength() {
        if(city.length() == 0){
            throw new InvalidRequestException("Cinema city is required");
        }
    }

    private void CheckNameLength() {
        if (name.length() == 0){
            throw new InvalidRequestException("Cinema name required");
        }
    }

    private void checkName() {
        if (this.name == null || this.name.trim().isEmpty())
            throw new InvalidRequestException("cinema name is required");
    }

    private void checkCity() {
        if (this.city == null || this.city.trim().isEmpty())
            throw new InvalidRequestException("cinema city location is required");
    }
    private void checkIsExist(){
        if(this.name == name && this.city == city){
            throw new InvalidRequestException("This cinema exist");
        }
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