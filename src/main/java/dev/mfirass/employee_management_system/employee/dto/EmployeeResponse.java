package dev.mfirass.employee_management_system.employee.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private String employeeId;
    private String fullName;
    private String jobTitle;
    private String department;
    private LocalDate hireDate;
    private String employmentStatus;
    private String email;
    private String phoneNumber;
    private String address;
}
