package org.binar.bioskop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketModel {
    
    private String username;
    private Integer scheduleId;
    private Character seatRow;
    private Integer seatNumber;
}
