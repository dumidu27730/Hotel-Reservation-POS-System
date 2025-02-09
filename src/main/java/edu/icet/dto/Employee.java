package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private Integer employeeId;
    private String jobRole;
    private String name;
    private String nicNumber;
    private String Address;
    private String phoneNumber;
    private Float salary;

    public Employee(String jobRole, String name, String nicNumber, String address, String phoneNumber, Float salary) {
        this.jobRole = jobRole;
        this.name = name;
        this.nicNumber = nicNumber;
        Address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


}

