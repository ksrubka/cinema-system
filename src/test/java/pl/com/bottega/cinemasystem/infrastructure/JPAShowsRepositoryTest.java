package pl.com.bottega.cinemasystem.infrastructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.Show;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/show.sql")
public class JPAShowsRepositoryTest {

    @Autowired
    private JPAShowsRepository jpaShowsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Collection<LocalDateTime> anyDatesList = new HashSet<>();

    private Movie anyMovie = new Movie();
    private Cinema anyCinema = new Cinema();
    private LocalDateTime anyDate = LocalDateTime.of(2016, 11, 23, 21, 00);
    private Set<String>   actors = new HashSet<>();
    private Set<String> genre = new HashSet<>();

    @Before
    public void setUp() {
        anyDatesList.add(anyDate);
    }

    @Test
    @Transactional
    public void shouldAddAndLoadShow() {
        //given

//
//        Show show = new Show(anyCinema, anyMovie, anyDatesList);
//        //when
//        jpaShowsRepository.save(show);
//        //then
//        jpaShowsRepository.load(show.getId());
//        assertEquals("name", show.getCinema().getName());
//    }
//    private void createShow() {
//        createShowsRequestInstance(anyMovieId, anyCinemaId, anyDatesList, anyCalendar);
//    }
    }
}
