package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;

    public CinemasController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog) {
        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    @GetMapping
    public ListAllCinemasResponse listAll() {
       return cinemaCatalog.listAll();
    }

    @GetMapping
    public ListMoviesInCinemaResponse listMoviesInCinema (Long cinemaId, LocalTime date){
        return null;
    }
}