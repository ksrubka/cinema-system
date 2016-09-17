package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateMovieRequestTest {

    private CreateMovieRequest createMovieRequest;
    @Mock
    private MovieDto movieDto;
    @Mock
    private MovieFactory movieFactory;
    private List<String> anyActors = new ArrayList<>();
    private String emptyString = "";
    private String anyTitle = "any title";
    private String anyDescription = "any desc";
    private Integer anyMinAge = 1;
    private List<String> anyGenres = new ArrayList<>();

    private Integer anyLength = 1;

    @Before
    public void setUp(){
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
    public void shouldNotValidateCinemaWithNameAsNull() {
        //given
        when(movieDto.getActors()).thenReturn(null);
        when(movieDto.getDescription()).thenReturn(anyDescription);
        when(movieDto.getGenres()).thenReturn(anyGenres);
        when(movieDto.getLength()).thenReturn(anyLength);
        when(movieDto.getTitle()).thenReturn(anyTitle);
        when(movieDto.getMinAge()).thenReturn(anyMinAge);
        createMovieRequest.setMovie(movieDto);

        //when
        createMovieRequest.validate();
    }
    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithEmptyDescription() {
        //given
        when(movieDto.getActors()).thenReturn(anyActors);
        when(movieDto.getDescription()).thenReturn(emptyString);
        when(movieDto.getGenres()).thenReturn(anyGenres);
        when(movieDto.getLength()).thenReturn(anyLength);
        when(movieDto.getTitle()).thenReturn(anyTitle);
        when(movieDto.getMinAge()).thenReturn(anyMinAge);
        createMovieRequest.setMovie(movieDto);

        //when
        createMovieRequest.validate();
    }

    private void createMovie(){
        when(movieDto.getActors()).thenReturn(anyActors);
        when(movieDto.getDescription()).thenReturn(anyDescription);
        when(movieDto.getGenres()).thenReturn(anyGenres);
        when(movieDto.getLength()).thenReturn(anyLength);
        when(movieDto.getTitle()).thenReturn(anyTitle);
        when(movieDto.getMinAge()).thenReturn(anyMinAge);
        createMovieRequest.setMovie(movieDto);
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
}
