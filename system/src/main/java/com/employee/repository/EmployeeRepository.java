package com.employee.repository;

import com.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "select * from employee where id=:empId",nativeQuery = true)
    Employee findById(@Param("empId") long empId);
}
