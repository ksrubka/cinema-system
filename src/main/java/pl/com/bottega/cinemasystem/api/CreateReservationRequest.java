package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.EmailValidator;
import pl.com.bottega.cinemasystem.api.utils.PhoneNumberValidator;
import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

import java.util.HashSet;
import java.util.Set;

public class CreateReservationRequest {

    private Long showId;
    private Set<TicketOrderDto> tickets;
    private Set<SeatDto> seats;
    private CustomerDto customer;

    public void validate(Integer minAge) {
        validateTicketOrder(minAge);
        validateSeats();
        validateCustomer();
    }

    void validateShowId() {
        ValidationUtils.validateId(showId, "Incorrect show id: " + showId);
    }

    private void validateTicketOrder(Integer minAge) {
        Set<String> ticketKinds = prepareTicketKindsForValidation();
        ValidationUtils.validateTicketKinds(ticketKinds, minAge);
    }

    private Set<String> prepareTicketKindsForValidation() {
        Set<String> ticketKinds = new HashSet<>();
        tickets.forEach(ticket -> ticketKinds.add(ticket.getKind()));
        return ticketKinds;
    }

    private void validateSeats() {
        seats.forEach(seat -> {
            if (seat.getRow() <= 0 || seat.getRow() > 10) {
                throw new InvalidRequestException("Incorrect row: " + seat.getRow());
            }
            if (seat.getSeat() <= 0 || seat.getSeat() > 15) {
                throw new InvalidRequestException("Incorrect seat: " + seat.getSeat());
            }
        });
    }

    private void validateCustomer() {
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidRequestException("Incorrect customer name");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InvalidRequestException("Incorrect customer last name");
        }
        validateEmail();
        validatePhoneNr();
    }

    private void validatePhoneNr() {
        if (customer.getPhone() == null) {
            throw new InvalidRequestException("customer phone nr is null");
        }
        PhoneNumberValidator validator = new PhoneNumberValidator();
        validator.validate(customer.getPhone());
    }

    private void validateEmail() {
        if (customer.getEmail() == null) {
            throw new InvalidRequestException("customer email is null");
        }
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(customer.getEmail());
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<TicketOrderDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrderDto> tickets) {
        this.tickets = tickets;
    }

    public Set<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDto> seats) {
        this.seats = seats;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
