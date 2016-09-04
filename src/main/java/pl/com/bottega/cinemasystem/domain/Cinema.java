package pl.com.bottega.cinemasystem.domain;

/**
 * Created by Nizari on 04.09.2016.
 */
public class Cinema {

    private String movie;
    private Long id;

    public Cinema(String movie, Long id ) {
        this.movie = movie;
        this.id = id;
    }
}
