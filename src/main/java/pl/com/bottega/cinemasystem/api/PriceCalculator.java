package pl.com.bottega.cinemasystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class PriceCalculator {

    private ShowsRepository showsRepository;

    public PriceCalculator(ShowsRepository showsRepository){
        this.showsRepository = showsRepository;
    }

    @Transactional
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        checkArgument(request != null);
        request.validate();
        Show show = showsRepository.load(request.getShowId());
        Set<TicketPrice> ticketPrices = show.getMovie().getTicketPrices();
        Set<TicketOrder> ticketOrders = request.getTickets();
        Calculation calculation = new Calculation(ticketOrders);
        calculation.calculatePrice(ticketPrices);
        return new CalculatePriceResponse(calculation, ticketOrders);
    }
}
