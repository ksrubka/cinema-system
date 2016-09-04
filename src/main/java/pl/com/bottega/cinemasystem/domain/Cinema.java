package pl.com.bottega.cinemasystem.domain;

/**
 * Created by Nizari on 04.09.2016.
 */
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
