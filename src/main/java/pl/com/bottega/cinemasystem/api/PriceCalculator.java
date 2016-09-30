package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Calculation;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Set;
import java.util.stream.Collectors;

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
        Set<TicketOrder> ticketOrder = createSetOfTicketOrders(request.getTickets());
        Calculation calculation = new Calculation(ticketOrder);
        show.calculatePrices(calculation);
        return new CalculatePriceResponse(calculation);
    }

    private Set<TicketOrder> createSetOfTicketOrders(Set<TicketOrderDto> tickets) {
        return tickets.stream().map(dto -> new TicketOrder(dto.getKind(), dto.getCount())).collect(Collectors.toSet());
    }
}
