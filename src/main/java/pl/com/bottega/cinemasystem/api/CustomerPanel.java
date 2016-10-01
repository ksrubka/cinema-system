package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.CinemaHall;
import pl.com.bottega.cinemasystem.domain.Reservation;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

import java.util.Set;

@Service
public class CustomerPanel {

    private ShowsRepository showsRepository;


    public CustomerPanel(ShowsRepository showsRepository) {
        this.showsRepository = showsRepository;
    }

    @Transactional
    public GetShowSeatsResponse getShowSeats(Long showId) {
        Show show = showsRepository.load(showId);
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        Set<SeatDto> spareSeats = cinemaHall.getSpareSeats();
        Set<SeatDto> occupiedSeats = cinemaHall.getOccupiedSeats();
        return new GetShowSeatsResponse(spareSeats, occupiedSeats);
    }
}
