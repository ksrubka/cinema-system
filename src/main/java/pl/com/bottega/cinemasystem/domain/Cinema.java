package pl.com.bottega.cinemasystem.domain;


public class Cinema {

    private String movie;
    private Long id;
    private int movielength;

    public Cinema(String movie, Long id, int movielength) {
        this.movie = movie;
        this.id = id;
        this.movielength = movielength;
    }
}
