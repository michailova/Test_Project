package com.example.Test_Projects.services;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.model.Project;

import java.util.List;

public interface EmployeeService {

       Employee addEmployee(Employee employee);

        Employee getEmployeeById(int id);

        List<Employee> getAllEmployees();

        Employee update(int id, Employee employee);

        String deleteEmployee(int id);


        List<Project> getProjectsByEmployeeId(int id);

        void saveEmployee(Employee employee);

    }


