package com.example.Test_Projects.controllers;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable(value = "id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        Employee result = null;
        try {
            result = employeeService.addEmployee(employee);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @PutMapping("/{id}")
    public Employee updateUser(@PathVariable(value = "id") int id, @RequestBody Employee employee) {
        Employee result = null;
        try {
            result = employeeService.update(id, employee);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id) {
        String message = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
