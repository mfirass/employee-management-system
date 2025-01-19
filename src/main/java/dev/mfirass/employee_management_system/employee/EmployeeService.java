package dev.mfirass.employee_management_system.employee;

import dev.mfirass.employee_management_system.employee.dto.EmployeeCreateRequest;
import dev.mfirass.employee_management_system.employee.dto.EmployeeResponse;
import dev.mfirass.employee_management_system.employee.dto.EmployeeUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
    EmployeeResponse getEmployeeById(String id);
    void deleteEmployee(String id);
    EmployeeResponse updateEmployee(String id, EmployeeUpdateRequest request);
    List<EmployeeResponse> searchEmployees(String search);
    List<EmployeeResponse> filterByDepartmentAndEmploymentStatus(String department, String employmentStatus);
}
