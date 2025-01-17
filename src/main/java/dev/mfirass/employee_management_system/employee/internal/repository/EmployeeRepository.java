package dev.mfirass.employee_management_system.employee.internal.repository;

import dev.mfirass.employee_management_system.employee.internal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
