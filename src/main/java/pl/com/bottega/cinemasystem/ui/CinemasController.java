package pl.com.bottega.cinemasystem.ui;

import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;

public class CinemasController {

    private AdminPanel adminPanel;

    public void create(CreateCinemaRequest createCinemaRequest) {
        adminPanel.createCinema(createCinemaRequest);
    }
}
