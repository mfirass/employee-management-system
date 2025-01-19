package dev.mfirass.employee_management_system.employee.internal.repository;

import dev.mfirass.employee_management_system.employee.internal.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE " +
            "(:search IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            " e.id = :search OR " +
            " LOWER(e.department) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            " LOWER(e.jobTitle) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            " LOWER(e.employmentStatus) LIKE LOWER(CONCAT('%', :search, '%'))) ")
    List<Employee> search(@Param("search") String search);
}
