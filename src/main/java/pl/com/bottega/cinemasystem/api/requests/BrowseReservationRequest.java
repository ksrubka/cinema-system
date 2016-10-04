package pl.com.bottega.cinemasystem.api.requests;

import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.ReservationStatus;

public class BrowseReservationRequest {

    private String status;
    private String lastName;

    public void validate() {
        validateStatus();
        validateName();
    }

    private void validateStatus() {
        if (status == null) {
            throw new InvalidRequestException("status can not be null");
        }
        try {
            ReservationStatus.valueOf(status.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Status is incorrect: " + status);
        }
    }

    private void validateName() {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("last name can not be null or empty");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStatusDefined() {
        return status != null;
    }

    public boolean isLastNameDefined() {
        return lastName != null;
    }
}
