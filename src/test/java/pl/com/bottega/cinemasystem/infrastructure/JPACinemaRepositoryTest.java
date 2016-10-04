package pl.com.bottega.cinemasystem.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
public class JPACinemaRepositoryTest {

    @Autowired
    private CinemaRepository jpaCinemaRepository;

    @Transactional
    @Test
    public void shouldAddAndLoadCinema(){
        //given
        Cinema cinema = new Cinema ("Imax", "Wrocław");
        //when
        jpaCinemaRepository.save(cinema);
        //then
        Cinema loadedCinema = jpaCinemaRepository.load(cinema.getId());
        assertEquals("Imax", loadedCinema.getName());
        assertEquals("Wrocław", loadedCinema.getCity());
    }
}

