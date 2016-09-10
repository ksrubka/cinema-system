package pl.com.bottega.cinemasystem.api;

public class CinemaDto {

    private String name;
    private String city;

    public CinemaDto() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaDto)) return false;

        CinemaDto cinemaDto = (CinemaDto) o;

        if (name != null ? !name.equals(cinemaDto.name) : cinemaDto.name != null) return false;
        return city != null ? city.equals(cinemaDto.city) : cinemaDto.city == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}