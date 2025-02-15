package edu.icet.dto;

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
public class Booking {
    private Integer bookingId;
    private Integer userId;
    private Integer guestId;
    private String roomNumber;
    private LocalDate checkInDate;
    private String checkInTime;
    private Integer days;
    private LocalDate checkOutDate;
    private Integer guestCount;
    private Float totalAmount;
    private String reservationStatus;

    public Booking(Integer userId, Integer guestId, String roomNumber, LocalDate checkInDate, String checkInTime, Integer days, LocalDate checkOutDate, Integer guestCount, Float totalAmount, String reservationStatus) {
        this.userId = userId;
        this.guestId = guestId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.days = days;
        this.checkOutDate = checkOutDate;
        this.guestCount = guestCount;
        this.totalAmount = totalAmount;
        this.reservationStatus = reservationStatus;
    }
}
