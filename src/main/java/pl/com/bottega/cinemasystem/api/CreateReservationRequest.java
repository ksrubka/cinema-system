package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.EmailValidator;
import pl.com.bottega.cinemasystem.api.utils.PhoneNumberValidator;
import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateReservationRequest {

    private ReservationDto reservation;

    public CustomerDto getCustomer() {
        return reservation.getCustomer();
    }

    public Set<SeatDto> getSeats() {
        return reservation.getSeats();
    }

    public Set<TicketOrderDto> getTickets() {
        return reservation.getTickets();
    }

    public ReservationDto getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDto reservation) {
        this.reservation = reservation;
    }

    public void validate(Integer minAge) {
        if (reservation == null) {
            throw new InvalidRequestException("Reservation is null");
        }
        reservation.validate(minAge);
    }

    public void validateShowId() {
        reservation.validateShowId();
    }

    public Long getShowId() {
        return reservation.getShowId();
    }

    public class ReservationDto {

        private Long showId;
        private Set<TicketOrderDto> tickets;
        private Set<SeatDto> seats;
        private CustomerDto customer;

        void validateShowId() {
            ValidationUtils.validateId(showId, "Incorrect show id: " + showId);
        }

        public void validate(Integer minAge) {
            validateTicketOrder(minAge);
            validateSeats();
            validateCustomer();
            checkIfNumberOfSeatsCorrespondsToNumberOfTickets();
        }

        private void validateTicketOrder(Integer minAge) {
            checkIfTicketsAreNotNull("No seats");
            Set<String> ticketKinds = prepareTicketKindsForValidation();
            validateTicketKinds(ticketKinds, minAge);
            validateTicketsQuantity();
        }

        public void checkIfTicketsAreNotNull(String msg) {
            checkNotNull(tickets);
            tickets.forEach(ticket -> {
                if (ticket.getCount() == null || ticket.getKind() == null) {
                    throw new InvalidRequestException(msg);
                }
            });
        }

        private void validateTicketsQuantity() {
            tickets.forEach(ticket -> {
                if (ticket.getCount() <= 0) {
                    throw new InvalidRequestException("Tickets quantity is too small");
                }
                if (countTickets() >= 150) {
                    throw new InvalidRequestException("Tickets quantity is too big");
                }
            });
        }

        private int countTickets() {
            int ticketsQuantity = 0;
            for (TicketOrderDto ticket : tickets) {
                ticketsQuantity += ticket.getCount();
            }
            return ticketsQuantity;
        }

        private Set<String> prepareTicketKindsForValidation() {
            Set<String> ticketKinds = new HashSet<>();
            tickets.forEach(ticket -> ticketKinds.add(ticket.getKind()));
            return ticketKinds;
        }

        private void validateTicketKinds(Set<String> tickets, Integer minAge) {
            if (ValidationUtils.minAgeIs18(minAge)) {
                if (tickets.contains("children") || tickets.contains("school")) {
                    throw new InvalidRequestException("Incorrect price types were declared, " +
                            "minimum age for movie is: " + minAge);
                }
            }
            if (ValidationUtils.minAgeIs16(minAge)) {
                if (tickets.contains("children")) {
                    throw new InvalidRequestException("Children ticket kind is not allowed " +
                            "when minimum age for movie is: " + minAge);
                }
            }
        }

        private void validateSeats() {
            checkIfSeatsAreNotNull("No seats");
            seats.forEach(seat -> {
                if (seat.getRow() <= 0 || seat.getRow() > 10) {
                    throw new InvalidRequestException("Incorrect row: " + seat.getRow());
                }
                if (seat.getNumber() <= 0 || seat.getNumber() > 15) {
                    throw new InvalidRequestException("Incorrect seat: " + seat.getNumber());
                }
            });
        }

        public void checkIfSeatsAreNotNull(String msg) {
            checkNotNull(seats);
            seats.forEach(seat -> {
                if (seat.getRow() == null || seat.getNumber() == null) {
                    throw new InvalidRequestException(msg);
                }
            });
        }

        private void validateCustomer() {
            checkNotNull(customer);
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

        private void checkIfNumberOfSeatsCorrespondsToNumberOfTickets() {
            int ticketsQuantity = countTickets();
            if (seats.size() != ticketsQuantity) {
                throw new InvalidRequestException("Tickets quantity does not correspond to number of reserved seats");
            }
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
}
