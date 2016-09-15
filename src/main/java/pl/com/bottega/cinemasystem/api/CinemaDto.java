package pl.com.bottega.cinemasystem.api;

public class CinemaDto {

    private Long id;
    private String name;
    private String city;

    public CinemaDto() {
    }

    public CinemaDto(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaDto)) return false;

        CinemaDto cinemaDto = (CinemaDto) o;

        if (id != null ? !id.equals(cinemaDto.id) : cinemaDto.id != null) return false;
        if (name != null ? !name.equals(cinemaDto.name) : cinemaDto.name != null) return false;
        return city != null ? city.equals(cinemaDto.city) : cinemaDto.city == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}