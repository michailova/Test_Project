package com.example.Test_Projects.services;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.model.Project;
import com.example.Test_Projects.repositorys.ProjectRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return savedProject;
    }

    @Override
    public Project getProjectById(int id) {
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();
            return project;
        }
        throw new RuntimeException("Project with id:" + id + " not exist");
    }


    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll().stream().toList();
    }


    @Override
    public Project update(int id, Project project) {
        Project projectUpdated = projectRepository.findById(id).get();
        projectUpdated.setId(id);
        projectUpdated.setName(project.getName());
        projectUpdated.setDetails(project.getDetails());
        projectUpdated.setEmployees(project.getEmployees());
        projectRepository.save(projectUpdated);
        return projectUpdated;
    }

    @Override
    public Project updateEmployees(int id, Project project) {
        Project projectUpdated = projectRepository.findById(id).get();
        projectUpdated.setId(id);
        Set<Employee> newList = projectUpdated.getEmployees();
        newList.addAll(project.getEmployees());
        projectUpdated.setEmployees(newList);
        projectRepository.save(projectUpdated);
        return projectUpdated;
    }

    @Override
    public String deleteProject(int id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(project.get().getId());
            return "Project with id: " + id + " deleted successfully!";
        }
        throw new RuntimeException("Could not delete project with id:" + id);
    }

    @Override
    public Set<Employee> getEmployeesByProjectId(int id) {
        Project project = projectRepository.getReferenceById(id);
        Set<Employee> employees = project.getEmployees();
        return employees;
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }
}
