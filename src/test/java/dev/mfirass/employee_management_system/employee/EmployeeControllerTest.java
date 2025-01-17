package dev.mfirass.employee_management_system.employee;

import dev.mfirass.employee_management_system.employee.dto.EmployeeCreateRequest;
import dev.mfirass.employee_management_system.employee.dto.EmployeeResponse;
import dev.mfirass.employee_management_system.employee.dto.EmployeeUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() {
        List<EmployeeResponse> employees = List.of(new EmployeeResponse());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<EmployeeResponse>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee() {
        String id = "123";
        EmployeeResponse employee = new EmployeeResponse();
        when(employeeService.getEmployeeById(id)).thenReturn(employee);

        ResponseEntity<EmployeeResponse> response = employeeController.getEmployeeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(id);
    }

    @Test
    void addEmployee_ShouldCreateAndReturnEmployee() {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        EmployeeResponse createdEmployee = new EmployeeResponse();
        when(employeeService.createEmployee(request)).thenReturn(createdEmployee);

        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdEmployee, response.getBody());
        verify(employeeService, times(1)).createEmployee(request);
    }

    @Test
    void updateEmployee_ShouldUpdateAndReturnEmployee() {
        String id = "123";
        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        EmployeeResponse updatedEmployee = new EmployeeResponse();
        when(employeeService.updateEmployee(id, request)).thenReturn(updatedEmployee);

        ResponseEntity<EmployeeResponse> response = employeeController.updateEmployee(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployee, response.getBody());
        verify(employeeService, times(1)).updateEmployee(id, request);
    }

    @Test
    void deleteEmployee_ShouldReturnNoContent() {
        String id = "123";

        ResponseEntity<Void> response = employeeController.deleteEmployee(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(id);
    }

    @Test
    void searchEmployees_ShouldReturnPagedResults() {
        String name = "John";
        String id = "123";
        String department = "Engineering";
        String jobTitle = "Developer";
        String employmentStatus = "Full-time";
        LocalDate hireDateFrom = LocalDate.of(2020, 1, 1);
        LocalDate hireDateTo = LocalDate.of(2023, 12, 31);
        Pageable pageable = Pageable.unpaged();
        Page<EmployeeResponse> employeePage = new PageImpl<>(Collections.singletonList(new EmployeeResponse()));

        when(employeeService.searchEmployees(name, id, department, jobTitle, employmentStatus, hireDateFrom, hireDateTo, pageable))
                .thenReturn(employeePage);

        ResponseEntity<Page<EmployeeResponse>> response = employeeController.searchEmployees(name, id, department, jobTitle, employmentStatus, hireDateFrom, hireDateTo, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeePage, response.getBody());
        verify(employeeService, times(1)).searchEmployees(name, id, department, jobTitle, employmentStatus, hireDateFrom, hireDateTo, pageable);
    }
}
