package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;

@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest createCinemaRequest) {
        adminPanel.createCinema(createCinemaRequest);
    }
}
