package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateShowsRequestTest {

    private Long movieId = 15L;
    private CalendarDto calendar;
    private Collection<String> dates = new ArrayList<>();
    private CreateShowsRequest createShowsRequest;
    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private ManyShowsDto manyShowsDto;

    private CreateShowsRequest createShowsRequest;

    private Long anyMovieId = 15L;
    private CalendarDto anyCalendar;
    private Collection<String> anyDates = new ArrayList<>();

    @Before
    public void setUp() {
        createShowsRequest = new CreateShowsRequest();
//        dates.add("2017/09/01 14:00"); // TODO: 18.09.2016 change to localDateTime
    }

    @Test
    public void shouldValidateShowWithCorrectShowDto() {
        //given
        when(showsDto.getCalendar()).thenReturn(calendar);
//        when(showsDto.getDates()).thenReturn(dates);
        when(showsDto.getMovieId()).thenReturn(movieId);
        createShowsRequest.setShows(showsDto);
        createShow();
        //when
        createShowsRequest.validate();
        //then
        assertShowTest();

    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateShowWithMovieIdAsNull() {
        //given
        createShowsRequestInstance(null, anyDates, anyCalendar);
        //when
        createShowsRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateShowWithDateAsNull() {
        //given
        createShowsRequestInstance(anyMovieId, null, anyCalendar);
        //when
        createShowsRequest.validate();
    }

    private void createShow() {
        createShowsRequestInstance(anyMovieId, anyDates, anyCalendar);
    }

    private void assertShowTest() {
        assertEquals(manyShowsDto.getMovieId(), createShowsRequest.getMovieId());
        assertEquals(manyShowsDto.getDates(), createShowsRequest.getDates());
        assertEquals(manyShowsDto.getCalendar(), createShowsRequest.getCalendar());
    }

    private void createShowsRequestInstance(Long movieId, Collection<String> dates, CalendarDto calendar) {
        manyShowsDto = new ManyShowsDto();
        createShowsRequest.setShows(manyShowsDto);
        manyShowsDto.setMovieId(movieId);
        manyShowsDto.setDates(dates);
        manyShowsDto.setCalendar(calendar);
    }
}
