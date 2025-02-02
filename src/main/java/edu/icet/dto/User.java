package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private Integer id;
    private String cmbPosition;
    private String name;
    private String nicNumber;
    private String email;
    private String username;
    private String phoneNumber;
    private String password;

    public User(String cmbPosition, String name, String nicNumber, String email, String username, String phoneNumber, String password) {
        this.cmbPosition = cmbPosition;
        this.name = name;
        this.nicNumber = nicNumber;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
