package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.ReservationStatus;

public class BrowseReservationRequest {

    private String status;
    private String lastName;

    public BrowseReservationRequest() {
    }

    public BrowseReservationRequest(String status, String lastName) {
        this.status = status;
        this.lastName = lastName;
    }

    public void validate() {
        validateStatus();
        validateName();
    }

    private void validateName() {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("last name can not be null or empty");
        }
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
}
