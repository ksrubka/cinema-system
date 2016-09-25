package pl.com.bottega.cinemasystem.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.api.CreateMovieRequest;

import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MovieFactoryTest {

    @Mock
    private CreateMovieRequest createMovieRequest;

    private MovieFactory movieFactory;

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
