package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.api.ListAllCinemasResponse;

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
    public void listAll(ListAllCinemasResponse response) {
        cinemaCatalog.listAll();

    }
}