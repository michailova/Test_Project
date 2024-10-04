package com.example.Test_Projects.services;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.model.Project;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    Project addProject(Project project);

    Project getProjectById(int id);

    List<Project> getAllProjects();

    Project update(int id, Project project);

    Project updateEmployees(int id, Project project);

    String deleteProject(int id);


    Set<Employee> getEmployeesByProjectId(int id);

    void saveProject(Project project);

}
