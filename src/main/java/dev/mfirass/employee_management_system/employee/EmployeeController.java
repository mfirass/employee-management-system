package dev.mfirass.employee_management_system.employee;

import dev.mfirass.employee_management_system.core.common.utils.ResourceUtils;
import dev.mfirass.employee_management_system.employee.dto.EmployeeCreateRequest;
import dev.mfirass.employee_management_system.employee.dto.EmployeeResponse;
import dev.mfirass.employee_management_system.employee.dto.EmployeeUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ResourceUtils.EMPLOYEE_ENDPOINT)
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") String id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeResponse);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable String id,
                                                           @RequestBody @Valid EmployeeUpdateRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String employmentStatus,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDateTo) {

        List<EmployeeResponse> employees = employeeService.searchEmployees(name, id, department, jobTitle, employmentStatus, hireDateFrom, hireDateTo);
        return ResponseEntity.ok(employees);
    }

}
