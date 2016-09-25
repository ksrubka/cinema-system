package pl.com.bottega.cinemasystem.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.CalendarDto;
import pl.com.bottega.cinemasystem.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/ticketprices.sql")
public class JPAShowsRepositoryTest {

    @Autowired
    private JPAShowsRepository showsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Long showId = 1L;

    @Test
    @Transactional
    public void shouldlistMoviesInCinema(){
        Set<TicketPrice> prices = showsRepository.loadListOfTicketPrices(showId);

        assertTrue(prices.size() == 2);
    }

}
