package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationEntity {
    private Integer reservationId;
    private String roomNumber;
    private String checkIn;
    private Integer days;
    private String checkOut;
    private String reservationStatus;
    private Float totalAmount;
}
