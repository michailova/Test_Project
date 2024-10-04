package com.example.Test_Projects.services;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.model.Project;
import com.example.Test_Projects.repositorys.EmployeeRepository;
import com.example.Test_Projects.repositorys.ProjectRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ProjectRepository projectRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int id) {
        if (employeeRepository.existsById(id)) {
            Employee employee = employeeRepository.findById(id).get();
            return employee;
        }
        throw new RuntimeException("Employee with id:" + id + " not exist");
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().toList();
    }

    @Override
    public Employee update(int id, Employee employee) {
        Employee emplUpdated = employeeRepository.findById(id).get();
        emplUpdated.setId(id);
        emplUpdated.setFirst_name(employee.getFirst_name());
        emplUpdated.setLast_name(employee.getLast_name());
        emplUpdated.setEmail(employee.getEmail());
        emplUpdated.setPatronymic(employee.getPatronymic());
        emplUpdated.setPosition(employee.getPosition());
        return emplUpdated;
    }

    @Override
    public String deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(employee.get().getId());
            return "User with id: " + id + " deleted successfully!";
        }
        throw new RuntimeException("Could not delete  user with id:" + id);
    }



    @Override
    public List<Project> getProjectsByEmployeeId(int id) {
        Employee employee = employeeRepository.getReferenceById(id);
        List<Project> projects = projectRepository.findAll();
        List<Project> employeeProjectList = new ArrayList<>();
        for (Project p :
                projects) {
            if (p.getEmployees().contains(employee)) {
               employeeProjectList.add(p);
            }
        }
        return employeeProjectList;
    }


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }


}
