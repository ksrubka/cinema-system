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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateShowsRequestTest {

    private Long movieId = 15L;
    private CalendarDto calendar;
    private Collection<String> dates = new ArrayList<>();
    private CreateShowsRequest createShowsRequest;
    @Mock
    private ShowsRepository showsRepository;
    @Mock
    private ShowsDto showsDto;

    @Before
    public void setUp(){
        createShowsRequest = new CreateShowsRequest();
//        dates.add("2017/09/01 14:00"); // TODO: 18.09.2016 change to localDateTime
    }
    @Test
    public void shouldValidateShow() {
        //given
        when(showsDto.getCalendar()).thenReturn(calendar);
//        when(showsDto.getDates()).thenReturn(dates);
        when(showsDto.getMovieId()).thenReturn(movieId);
        createShowsRequest.setShows(showsDto);
        //when
        createShowsRequest.validate();
        //then
        assertEquals(showsDto, createShowsRequest.getShows());

    }
//    @Test(expected = InvalidRequestException.class)
//    public void shouldNotCreateShowWithInvalidMovieId(){
//        when(showsDto.getCalendar()).thenReturn(calendar);
//        when(showsDto.getDates()).thenReturn(dates);
//        when(showsDto.getMovieId()).thenReturn(null);
//        createShowsRequest.setShows(showsDto);
//        //when
//        createShowsRequest.validate();
//    }

}
