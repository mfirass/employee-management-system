package dev.mfirass.employee_management_system.employee.internal.implementation;

import dev.mfirass.employee_management_system.core.exception.EmployeeNotFoundException;
import dev.mfirass.employee_management_system.employee.EmployeeService;
import dev.mfirass.employee_management_system.employee.dto.EmployeeCreateRequest;
import dev.mfirass.employee_management_system.employee.dto.EmployeeResponse;
import dev.mfirass.employee_management_system.employee.dto.EmployeeUpdateRequest;
import dev.mfirass.employee_management_system.employee.internal.entity.Employee;
import dev.mfirass.employee_management_system.employee.internal.mapper.EmployeeMapper;
import dev.mfirass.employee_management_system.employee.internal.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.
                findAll().
                stream().
                map(employeeMapper::toResponse).
                toList();
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Employee savedEmployee = employeeRepository.save(employeeMapper.toEntity(request));
        return employeeMapper.toResponse(savedEmployee);    }

    @Override
    public EmployeeResponse getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toResponse)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
    }

    @Override
    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponse updateEmployee(String id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
        employeeMapper.updateEmployeeFromRequest(request, employee);
        employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }
}
