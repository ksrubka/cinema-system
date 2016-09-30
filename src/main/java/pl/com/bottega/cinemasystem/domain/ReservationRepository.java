package pl.com.bottega.cinemasystem.domain;

public interface ReservationRepository {
    void save(Reservation reservation);
    Reservation load(String number);
}
