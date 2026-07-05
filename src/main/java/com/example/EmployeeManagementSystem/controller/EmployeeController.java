package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id).orElseThrow();

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());

        return employeeRepository.save(employee);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }
    @GetMapping("/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeRepository.findByName(name);
    }
    @GetMapping("/email/{email}")
    public List<Employee> getEmployeesByEmail(@PathVariable String email) {
        return employeeRepository.findByEmail(email);
    }
    @GetMapping("/all-query")
    public List<Employee> getAllEmployeesUsingQuery() {
        return employeeRepository.getAllEmployees();
    }


    @GetMapping("/page")
    public Page<Employee> getEmployeesPage(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
    @GetMapping("/sort")
    public List<Employee> getEmployeesSorted() {
        return employeeRepository.findAll(
                Sort.by("name").ascending()
        );
    }

    @GetMapping("/page-sort")
    public Page<Employee> getEmployeesPageAndSort(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("name").ascending());

        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/jpql/name/{name}")
    public List<Employee> getEmployeesByNameJPQL(
            @PathVariable String name) {
        return employeeRepository.findEmployeesByName(name);
    }

    @GetMapping("/jpql/email/{email}")
    public List<Employee> getEmployeesByEmailJPQL(
            @PathVariable String email) {
        return employeeRepository.findEmployeesByEmail(email);
    }

    @GetMapping("/native/all")
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }

    @GetMapping("/native/name/{name}")
    public List<Employee> getEmployeeByNameNative(
            @PathVariable String name) {
        return employeeRepository.findEmployeeByNameNative(name);
    }
}