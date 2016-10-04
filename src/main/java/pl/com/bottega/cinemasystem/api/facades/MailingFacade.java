package pl.com.bottega.cinemasystem.api.facades;

import pl.com.bottega.cinemasystem.domain.Reservation;

public interface MailingFacade {

    void sendEmail(Reservation reservation);
}
