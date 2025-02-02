package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserEntity {
    private String id;
    private String cmbPosition;
    private String name;
    private String nicNumber;
    private String email;
    private String username;
    private String phoneNumber;
    private String password;
}
