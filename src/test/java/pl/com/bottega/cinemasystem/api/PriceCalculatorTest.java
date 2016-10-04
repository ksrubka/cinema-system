package pl.com.bottega.cinemasystem.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.api.dtos.TicketOrderDto;
import pl.com.bottega.cinemasystem.api.requests.CalculatePriceRequest;
import pl.com.bottega.cinemasystem.api.services.PriceCalculator;
import pl.com.bottega.cinemasystem.domain.*;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceCalculatorTest {

    @Mock
    private Show show;

    @Mock
    private Movie movie;

    @Mock
    private ShowsRepository showsRepository;

    private CalculatePriceRequest calculatePriceRequest = new CalculatePriceRequest();
    private PriceCalculator priceCalculator;

    private Long anyShowId = 1L;
    private TicketOrderDto ticketOrderDto1 = new TicketOrderDto();
    private TicketOrderDto ticketOrderDto2 = new TicketOrderDto();
    private TicketOrderDto ticketOrderDto3 = new TicketOrderDto();
    private TicketOrderDto ticketOrderDto4 = new TicketOrderDto();
    private TicketPrice ticketPrice1 = new TicketPrice("regular", new BigDecimal(20));
    private TicketPrice ticketPrice2 = new TicketPrice("children", new BigDecimal(12));
    private TicketPrice ticketPrice3 = new TicketPrice("school", new BigDecimal(10));
    private TicketPrice ticketPrice4 = new TicketPrice("student", new BigDecimal(15));

    @Before
    public void setUp() {
        priceCalculator = new PriceCalculator(showsRepository);
        ticketOrderDto1.setKind("regular");
        ticketOrderDto1.setCount(4);
        ticketOrderDto2.setKind("children");
        ticketOrderDto2.setCount(4);
        ticketOrderDto3.setKind("school");
        ticketOrderDto3.setCount(4);
        ticketOrderDto4.setKind("student");
        ticketOrderDto4.setCount(4);

    }

    @Test
    public void shouldCalculatePrice() {
        //given
        calculatePriceRequest.setShowId(anyShowId);
        calculatePriceRequest.setTickets(Sets.newHashSet(ticketOrderDto1, ticketOrderDto2, ticketOrderDto3, ticketOrderDto4));
        //when
        when(showsRepository.load(anyShowId)).thenReturn(show);
        when(showsRepository.loadListOfTicketPrices(calculatePriceRequest.getShowId())).thenReturn(Sets.newHashSet(ticketPrice1, ticketPrice2, ticketPrice3, ticketPrice4));
        //then
        priceCalculator.calculatePrice(calculatePriceRequest);
    }

}
