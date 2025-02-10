package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {
    private Integer bookingId;
    private Integer userId;
    private Integer guestId;
    private String roomNumber;
    private Date checkInDate;
    private String checkInTime;
    private Integer days;
    private Date checkInOut;
    private Integer guestCount;
    private Float totalAmount;
    private String reservationStatus;
}
