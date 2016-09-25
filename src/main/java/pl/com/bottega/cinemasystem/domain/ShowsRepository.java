package pl.com.bottega.cinemasystem.domain;

import java.util.Set;

public interface ShowsRepository {

    void save(Show show);
    Show load(Long id);
    Set<TicketPrice> loadListOfTicketPrices(Long showId);
    void findShowById(Long showId);
    void findShowWithReservation(Long showId);
}
