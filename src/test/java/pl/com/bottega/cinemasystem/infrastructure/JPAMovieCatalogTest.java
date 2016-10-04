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
import pl.com.bottega.cinemasystem.api.responses.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.catalogs.MovieCatalog;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml","/mock-auth-context.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/moviesInCinema.sql")
public class JPAMovieCatalogTest {

    @Autowired
    private MovieCatalog jpaMovieCatalog;

    private Long testId = 4L;
    private LocalDate testDate = LocalDate.of(2016, 9, 20);

    @Transactional
    @Test
    @Sql("/fixtures/moviesInCinema.sql")
    public void shouldListAllMoviesInCinema(){
        ListMoviesInCinemaResponse response = jpaMovieCatalog.listMoviesInCinema(testId, testDate);
        assertNotNull(response.getMovies());

    }

}
