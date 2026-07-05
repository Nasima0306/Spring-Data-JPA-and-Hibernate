package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);


    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployees();


}
