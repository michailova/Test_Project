package com.example.Test_Projects.controllers;

import com.example.Test_Projects.model.Project;
import com.example.Test_Projects.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Project findProjectById(@PathVariable(value = "id") int id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        Project result = null;
        try {
            result = projectService.addProject(project);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable(value = "id") int id, @RequestBody Project project) {
        Project result = null;
        try {
            result = projectService.update(id, project);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable(name = "id") int id) {
        String message = projectService.deleteProject(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
