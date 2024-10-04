package com.example.Test_Projects.controllers.mvc;

import com.example.Test_Projects.model.Employee;
import com.example.Test_Projects.model.Project;
import com.example.Test_Projects.services.EmployeeService;
import com.example.Test_Projects.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectControllerMVC {

    Logger logger = LoggerFactory.getLogger(ProjectControllerMVC.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = {"/", "/list"})
    public String listProjects(ModelMap model) {

        List<Project> projects = projectService.getAllProjects();

        model.addAttribute("projects", projects);
        return "projectslist";

    }

    @GetMapping("/addproject")
    public String newProject(Model model) {
        logger.info("addproject started");
        Project project = new Project();
        model.addAttribute("project", project);
        return "newproject";
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String saveProject(@Validated Project project, BindingResult result,
                               ModelMap model) {

        projectService.addProject(project);
        model.addAttribute("success", "Project " + project.getName() + " add successfully");
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projectslist";
    }

    @RequestMapping(value = {"/assign-project-{id}"}, method = RequestMethod.GET)
    public String assignToEmployee(Model model, @PathVariable int id) {
        logger.info("assignToEmployee started");
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "assign";
    }

    @RequestMapping(value = {"/assign-project-{id}"}, method = RequestMethod.POST)
    public String assignToEmployee(Project project, BindingResult result,
                                 ModelMap model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            return "error";
        }
        projectService.updateEmployees(id, project);
        model.addAttribute("success", "In project " + project.getName() + " the composition of participants has been changed");
        return "redirect:/projects/list";
    }

    @ModelAttribute("employeeslist")
    public List<Employee> initializeEmployeesList() {
        return employeeService.getAllEmployees();
    }

}
