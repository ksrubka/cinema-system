package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Movie;

import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MovieFactoryTest {

    private MovieFactory movieFactory;
    @Mock
    private CreateMovieRequest createMovieRequest;

    @Before
    public void SetUp() {
        movieFactory = new MovieFactory();
    }

    @Test
    public void shouldCreateMovie() {
        Movie movie = movieFactory.createMovie(createMovieRequest);
        assertNotNull(movie);
    }


}
