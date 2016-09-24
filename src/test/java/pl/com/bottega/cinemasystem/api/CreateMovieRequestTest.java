package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.MovieFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateMovieRequestTest {

    @Mock
    private MovieDto movieDto;

    @Mock
    private MovieFactory movieFactory;

    private CreateMovieRequest createMovieRequest;

    private String emptyString = " ";
    private String anyTitle = "any title";
    private String anyDescription = "any desc";
    private Integer anyMinAge = 1;
    private Set<String> anyActors = new HashSet<>();
    private Set<String> anyGenres = new HashSet<>();
    private Integer anyLength = 1;

    @Before
    public void setUp() {
        createMovieRequest = new CreateMovieRequest();
        anyActors.add("Andrzej Grabowski");
        anyGenres.add("horror");
    }

    @Test
    public void shouldValidateMovieWithCorrectMovieDto() {
        //given
        createMovie();
        //when
        createMovieRequest.validate();
        //then
        assertMovieTest();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateMovieWithTitleAsNull() {
        //given
        createMovieRequestInstance(null, anyDescription, anyMinAge, anyActors, anyGenres, anyLength);

        //when
        createMovieRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateMovieWithDescriptionAsNull() {
        //given
        createMovieRequestInstance(anyTitle, null, anyMinAge, anyActors, anyGenres, anyLength);

        //when
        createMovieRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateMovieWithActorsAsNull() {
        //given
        createMovieRequestInstance(anyTitle, anyDescription, anyMinAge, null, anyGenres, anyLength);

        //when
        createMovieRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateMovieWithEmptyTitle() {
        //given
        createMovieRequestInstance(emptyString, anyDescription, anyMinAge, anyActors, anyGenres, anyLength);

        //when
        createMovieRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateMovieWithEmptyDescription() {
        //given
        createMovieRequestInstance(anyTitle, emptyString, anyMinAge, anyActors, anyGenres, anyLength);

        //when
        createMovieRequest.validate();
    }

    private void createMovie() {
        createMovieRequestInstance(anyTitle, anyDescription, anyMinAge, anyActors, anyGenres, anyLength);
    }

    private void assertMovieTest() {
        assertEquals(movieDto.getActors(), createMovieRequest.getActors());
        assertEquals(movieDto.getDescription(), createMovieRequest.getDescription());
        assertEquals(movieDto.getGenres(), createMovieRequest.getGenres());
        assertEquals(movieDto.getLength(), createMovieRequest.getLength());
        assertEquals(movieDto.getTitle(), createMovieRequest.getTitle());
        assertEquals(movieDto.getMinAge(), createMovieRequest.getMinAge());
        assertEquals(movieDto, createMovieRequest.getMovie());
    }

    private void createMovieRequestInstance(String title, String description, Integer minAge, Set<String> actors, Set<String> genres, Integer length) {
        movieDto = new MovieDto();
        createMovieRequest.setMovie(movieDto);
        movieDto.setTitle(title);
        movieDto.setDescription(description);
        movieDto.setMinAge(minAge);
        movieDto.setActors(actors);
        movieDto.setGenres(genres);
        movieDto.setLength(length);
    }
}
