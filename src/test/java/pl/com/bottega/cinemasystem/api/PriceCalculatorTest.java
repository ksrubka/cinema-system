package pl.com.bottega.cinemasystem.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.*;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceCalculatorTest {

    @Mock
    private Show show;

    @Mock
    private TicketPricesFactory ticketPricesFactory;

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private CalculatePriceRequest calculatePriceRequest;


    private PriceCalculator priceCalculator;

    private Long showId = 1L;
    private TicketOrder ticketOrder1 = new TicketOrder();
    private TicketOrder ticketOrder2 = new TicketOrder();
    private TicketOrder ticketOrder3 = new TicketOrder();
    private TicketPrice ticketPrice1 = new TicketPrice("regular", new BigDecimal(20));
    private TicketPrice ticketPrice2 = new TicketPrice("children", new BigDecimal(12));
    private TicketPrice ticketPrice3 = new TicketPrice("school", new BigDecimal(10));

    @Before
    public void setUp() {
        priceCalculator = new PriceCalculator(showsRepository);
        ticketOrder1.setKind("regular");
        ticketOrder1.setCount(3);
        ticketOrder2.setKind("children");
        ticketOrder2.setCount(3);
        ticketOrder3.setKind("school");
        ticketOrder3.setCount(3);
    }

    @Test
    public void shouldCalculatePrice() {
        //given
        calculatePriceRequest.setShowId(showId);
        calculatePriceRequest.setTickets(Sets.newHashSet(ticketOrder1, ticketOrder2, ticketOrder3));
        //when
        when(showsRepository.load(showId)).thenReturn(show);
        when(showsRepository);
        //then
        priceCalculator.calculatePrice(calculatePriceRequest);

    }

}
