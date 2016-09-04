package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.api.ListAllCinemasResponse;

@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;

    public CinemasController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    public void listAll(ListAllCinemasResponse response) {

    }
}