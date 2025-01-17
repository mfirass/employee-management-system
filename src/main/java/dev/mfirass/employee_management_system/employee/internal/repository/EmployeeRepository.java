package dev.mfirass.employee_management_system.employee.internal.repository;

import dev.mfirass.employee_management_system.employee.internal.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:department IS NULL OR LOWER(e.department) = LOWER(:department)) AND " +
            "(:jobTitle IS NULL OR LOWER(e.jobTitle) = LOWER(:jobTitle)) AND " +
            "(:employmentStatus IS NULL OR LOWER(e.employmentStatus) = LOWER(:employmentStatus)) AND " +
            "(:hireDateFrom IS NULL OR e.hireDate >= :hireDateFrom) AND " +
            "(:hireDateTo IS NULL OR e.hireDate <= :hireDateTo)")
    Page<Employee> search(@Param("name") String name,
                          @Param("id") String id,
                          @Param("department") String department,
                          @Param("jobTitle") String jobTitle,
                          @Param("employmentStatus") String employmentStatus,
                          @Param("hireDateFrom") LocalDate hireDateFrom,
                          @Param("hireDateTo") LocalDate hireDateTo,
                          Pageable pageable);
}
