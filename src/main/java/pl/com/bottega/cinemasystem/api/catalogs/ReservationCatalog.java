package pl.com.bottega.cinemasystem.api.catalogs;

import pl.com.bottega.cinemasystem.api.requests.BrowseReservationRequest;
import pl.com.bottega.cinemasystem.api.responses.BrowseReservationResponse;

public interface ReservationCatalog {

    BrowseReservationResponse browseReservations(BrowseReservationRequest request);
}
