package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RoomEntity {
    private String roomNumber;
    private String roomType;
    private String description;
    private Float pricePerNight;
    private String availableStatus;
}
