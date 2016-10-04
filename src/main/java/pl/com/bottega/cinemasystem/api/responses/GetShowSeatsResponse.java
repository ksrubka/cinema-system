package pl.com.bottega.cinemasystem.api.responses;

import pl.com.bottega.cinemasystem.api.dtos.SeatDto;

import java.util.Set;

public class GetShowSeatsResponse {

    private Set<SeatDto> free;
    private Set<SeatDto> occupied;

    public GetShowSeatsResponse(Set<SeatDto> spareSeats, Set<SeatDto> occupiedSeats) {
        this.free = spareSeats;
        this.occupied = occupiedSeats;
    }

    public Set<SeatDto> getFree() {
        return free;
    }

    public void setFree(Set<SeatDto> free) {
        this.free = free;
    }

    public Set<SeatDto> getOccupied() {
        return occupied;
    }

    public void setOccupied(Set<SeatDto> occupied) {
        this.occupied = occupied;
    }
}
