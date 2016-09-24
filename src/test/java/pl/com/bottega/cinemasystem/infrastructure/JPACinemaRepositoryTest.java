package pl.com.bottega.cinemasystem.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/cinemas.sql")
public class JPACinemaRepositoryTest {
    @Autowired
    private CinemaRepository jpaCinemaRepository;

    @Transactional
    @Test
    public void shouldAddCinema(){
        Cinema cinema = new Cinema ("CinemaCity", "Warszawa");

        jpaCinemaRepository.save(cinema);

        Cinema loadedCinema = jpaCinemaRepository.load(cinema.getId());
        assertEquals("CinemaCity", loadedCinema.getName());
        assertEquals("Warszawa", loadedCinema.getCity());
    }

}

