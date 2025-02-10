package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingEntity {
    private Integer bookingId;
    private Integer userId;
    private Integer guestId;
    private String roomNumber;
    private LocalDate checkInDate;
    private String checkInTime;
    private Integer days;
    private LocalDate checkInOut;
    private Integer guestCount;
    private Float totalAmount;
    private String reservationStatus;
}
