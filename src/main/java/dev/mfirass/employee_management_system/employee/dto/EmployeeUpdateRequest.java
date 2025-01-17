package dev.mfirass.employee_management_system.employee.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeUpdateRequest {
    private String fullName;
    private String jobTitle;
    private String department;
    private LocalDate hireDate;
    private String employmentStatus;

    @Email(message = "Invalid email format")
    private String email;

    private String phoneNumber;
    private String address;
}

