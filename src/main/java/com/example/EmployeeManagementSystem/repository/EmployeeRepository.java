package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);


    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployees();

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findEmployeesByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    List<Employee> findEmployeesByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM employee",
            nativeQuery = true
    )
    List<Employee> getAllEmployeesNative();


    @Query(
            value = "SELECT * FROM employee WHERE name = ?1",
            nativeQuery = true
    )
    List<Employee> findEmployeeByNameNative(String name);


}
