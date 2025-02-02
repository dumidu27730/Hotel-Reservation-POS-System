package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class GuestEntity{
    private String id;
    private String name;
    private String nicNumber;
    private String address;
    private String guestRequest;
    private String email;
    private String phoneNumber;
}
