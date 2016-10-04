package pl.com.bottega.cinemasystem.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.catalogs.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.catalogs.MovieCatalog;
import pl.com.bottega.cinemasystem.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.api.responses.ListAllCinemasResponse;
import pl.com.bottega.cinemasystem.api.responses.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.services.AdminPanel;

import java.time.LocalDate;

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

    @GetMapping("/{cinemaId}/movies")
    public ListMoviesInCinemaResponse listMoviesInCinema(
            @PathVariable Long cinemaId,
            @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date) {
        return movieCatalog.listMoviesInCinema(cinemaId, date);
    }
}