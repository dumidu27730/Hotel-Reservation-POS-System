package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity {
    private Integer employeeId;
    private String jobRole;
    private String name;
    private String nicNumber;
    private String Address;
    private String phoneNumber;
    private Float salary;
}
