package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;
    private MovieCatalog movieCatalog;

    public CinemasController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog, MovieCatalog movieCatalog) {
        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
        this.movieCatalog = movieCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    @GetMapping
    public ListAllCinemasResponse listAll() {
       return cinemaCatalog.listAll();
    }

    @GetMapping("/{cinemaId}")
    public ListMoviesInCinemaResponse listMoviesInCinema (@PathVariable Long cinemaId, LocalTime date){
        return movieCatalog.listMoviesInCinema(cinemaId, date);
    }
}