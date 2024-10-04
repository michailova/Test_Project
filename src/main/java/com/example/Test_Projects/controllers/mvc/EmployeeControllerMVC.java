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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeControllerMVC {

    Logger logger = LoggerFactory.getLogger(EmployeeControllerMVC.class);

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/addemployee")
    public String newEmployee(Model model) {
        logger.info("addemployee started");
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newemployee";
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String saveEmployee(@Validated Employee employee, BindingResult result,
                              ModelMap model) {
        if (result.hasErrors()) {
            return "error";

        }
        employeeService.addEmployee(employee);
        model.addAttribute("msg", "Employee " + employee.toString() + " add successfully");
        return "success";
    }

}
