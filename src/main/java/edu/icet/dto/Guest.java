package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Guest {
    private Integer id;
    private String name;
    private String nicNumber;
    private String address;
    private String guestRequest;
    private String email;
    private String phoneNumber;

    public Guest(String name, String nicNumber, String address, String guestRequest, String email, String phoneNumber) {
        this.name = name;
        this.nicNumber = nicNumber;
        this.address = address;
        this.guestRequest = guestRequest;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


}
