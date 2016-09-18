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

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private ManyShowsDto manyShowsDto;

    private CreateShowsRequest createShowsRequest;

    private Long anyMovieId = 15L;
    private CalendarDto anyCalendar;
//    private Collection<String> anyDates = new ArrayList<>();

    @Before
    public void setUp() {
        createShowsRequest = new CreateShowsRequest();
//        anyDates.add("2017/09/01 14:00");
    }

    @Test
    public void shouldValidateShowWithCorrectShowDto() {
        //given
        createShow();
        //when
        createShowsRequest.validate();
        //then
        assertShowTest();

    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateShowWithMovieIdAsNull() {
        //given
//        createShowsRequestInstance(null, anyDates, anyCalendar);
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
//        createShowsRequestInstance(anyMovieId, anyDates, anyCalendar);
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
//        manyShowsDto.setDates(dates); //// TODO: 18.09.2016  
        manyShowsDto.setCalendar(calendar);
    }
}