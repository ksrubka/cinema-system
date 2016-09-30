package pl.com.bottega.cinemasystem.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.api.ReservationDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

    @Mock
    private Show show;

    @Mock
    private ReservationDto reservationDto;

    @Test
    public void shouldMakeReservation() {
        //given
        Set<TicketOrder> ticketOrder = new HashSet<>(Arrays.asList(new TicketOrder("children", 10),
                new TicketOrder("children", 10), new TicketOrder("regular", 20)));
        Set<Seat> bookedSeats = new HashSet<>(Arrays.asList(new Seat(2, 2), new Seat(3, 3)));
        Customer customer = new Customer("Ryszard", "Marchewka", "MarchewaBest@epicmail.com", "070072772");
        BigDecimal totalPrice = new BigDecimal(100);

        //when
        Reservation reservation = new Reservation(ticketOrder, bookedSeats, customer, totalPrice, show);

        //then
        assertEquals(ticketOrder, reservation.getTickets());
        assertEquals(bookedSeats, reservation.getBookedSeats());
        assertEquals(customer, reservation.getCustomer());
        assertEquals(ReservationStatus.PENDING, reservation.getStatus());
    }

}