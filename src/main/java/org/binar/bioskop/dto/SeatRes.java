package org.binar.bioskop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRes {
    private Character seatRow;
    private Integer seatNumber;
    private Integer StudioId;
    private boolean seatStatus;
}
