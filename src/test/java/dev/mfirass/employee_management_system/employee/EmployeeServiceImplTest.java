package dev.mfirass.employee_management_system.employee;

import dev.mfirass.employee_management_system.core.exception.EmployeeNotFoundException;
import dev.mfirass.employee_management_system.employee.dto.EmployeeCreateRequest;
import dev.mfirass.employee_management_system.employee.dto.EmployeeResponse;
import dev.mfirass.employee_management_system.employee.dto.EmployeeUpdateRequest;
import dev.mfirass.employee_management_system.employee.internal.entity.Employee;
import dev.mfirass.employee_management_system.employee.internal.implementation.EmployeeServiceImpl;
import dev.mfirass.employee_management_system.employee.internal.mapper.EmployeeMapper;
import dev.mfirass.employee_management_system.employee.internal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_shouldReturnEmployeeResponses() {
        List<Employee> employees = List.of(new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.toResponse(any(Employee.class))).thenReturn(new EmployeeResponse());

        List<EmployeeResponse> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    void createEmployee_shouldReturnCreatedEmployeeResponse() {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        Employee employee = new Employee();
        when(employeeMapper.toEntity(request)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toResponse(employee)).thenReturn(new EmployeeResponse());

        EmployeeResponse result = employeeService.createEmployee(request);

        assertNotNull(result);
        verify(employeeRepository).save(employee);
    }

    @Test
    void getEmployeeById_whenFound_shouldReturnEmployeeResponse() {
        Employee employee = new Employee();
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));
        when(employeeMapper.toResponse(employee)).thenReturn(new EmployeeResponse());

        EmployeeResponse result = employeeService.getEmployeeById("1");

        assertNotNull(result);
        verify(employeeRepository).findById("1");
    }

    @Test
    void getEmployeeById_whenNotFound_shouldThrowException() {
        when(employeeRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById("1"));
    }

    @Test
    void deleteEmployee_whenExists_shouldDeleteEmployee() {
        when(employeeRepository.existsById("1")).thenReturn(true);

        employeeService.deleteEmployee("1");

        verify(employeeRepository).deleteById("1");
    }

    @Test
    void deleteEmployee_whenNotExists_shouldThrowException() {
        when(employeeRepository.existsById("1")).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee("1"));
    }

    @Test
    void updateEmployee_whenFound_shouldReturnUpdatedEmployee() {
        Employee employee = new Employee();
        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));
        doNothing().when(employeeMapper).updateEmployeeFromRequest(request, employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toResponse(employee)).thenReturn(new EmployeeResponse());

        EmployeeResponse result = employeeService.updateEmployee("1", request);

        assertNotNull(result);
        verify(employeeRepository).save(employee);
    }

    @Test
    void searchEmployees_shouldReturnPagedEmployees() {
        Page<Employee> page = new PageImpl<>(List.of(new Employee()));
        Pageable pageable = PageRequest.of(0, 10);
        when(employeeRepository.search(null, null, null, null, null, null, null, pageable)).thenReturn(page);
        when(employeeMapper.toResponse(any(Employee.class))).thenReturn(new EmployeeResponse());

        Page<EmployeeResponse> result = employeeService.searchEmployees(null, null, null, null, null, null, null, pageable);

        assertEquals(1, result.getTotalElements());
        verify(employeeRepository).search(null, null, null, null, null, null, null, pageable);
    }
}
