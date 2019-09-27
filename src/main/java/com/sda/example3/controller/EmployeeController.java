package com.sda.example3.controller;

import com.sda.example3.model.*;
import com.sda.example3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping("/add")
//    public String addEmployee(@RequestParam String imie,
//                            @RequestParam String nazwisko,
//                            @RequestParam int zarobki) {
//
//        Employee employee = new Employee(imie, nazwisko, new BigDecimal(zarobki));
//        employeeRepository.save(employee);
//
//        return "Pracownik dodany do bazy";
//    }

//    Dodawanie pracownika
    @GetMapping("/add")
    public String addEmployeeForm(Model model) {

        EmployeeAddDTO form = new EmployeeAddDTO();
        model.addAttribute("form", form);

        return "employee-add";

    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("form") @Valid EmployeeAddDTO form, BindingResult result) {

        if (result.hasErrors()) {

            return "employee-add";

        }
        Employee employee = new Employee(
                form.getFirstName(),
                form.getLastName(),
                new BigDecimal(form.getSalary())
        );

        employeeRepository.save(employee);

        return "redirect:/employee/add-success";
    }

    @PostMapping()

    @GetMapping("/add-success")
    public String employeeAddSuccess() {
        return "employee-add-success";
    }

//    Szukanie pracownika
    
    @GetMapping("/find")
    public String findEmployeeByNameForm(Model model) {
        EmployeeFindByNameDTO form = new EmployeeFindByNameDTO();
        model.addAttribute("form", form);

        return "employee-find";
    }

    @ResponseBody
    @PostMapping("/find")
    public String findEmployeeByName(@ModelAttribute("form")
                                     @Valid EmployeeFindByNameDTO form,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "employee-find";
        }

        List<Employee> employees = new ArrayList<>();
        employees = employeeRepository.findByFirstName(form.getFirstName());
        for (Employee e : employees) {
            System.out.println(e);
        }
        return employees.toString();


    }

    @GetMapping("/delete")
    public String deleteEmployeeForm(Model model) {
        EmployeeDeleteDTO form = new EmployeeDeleteDTO();
        model.addAttribute("form", form);
        return "employee-delete";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@ModelAttribute("form")
                                 @Valid EmployeeDeleteDTO form,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "employee-delete";
        }

        List<Employee> employees = new ArrayList<>();
        employees = employeeRepository.findByFirstNameAndLastName(
                form.getFirstName(), form.getLastName());

        for (Employee e : employees) {
            employeeRepository.delete(e);
        }
        return "employee-deleted";
    }

    @GetMapping("/update")
    public String updateEmployeeForm(Model model) {
        EmployeeUpdateDTO form = new EmployeeUpdateDTO();

        model.addAttribute("form", form);
        System.out.println(form);

        return "employee-update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("form")
                                 @Valid EmployeeUpdateDTO form,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "employee-update";
        }


        List<Employee> employees = new ArrayList<>();

        employees = employeeRepository.findByFirstNameAndLastName(
          form.getFirstName1(),form.getLastName1());

        BigDecimal salary;

        for (Employee e :employees) {
            //System.out.println(e);
            salary = e.getSalary();
            employeeRepository.delete(e);
            e.setFirstName(form.getFirstName2());
            e.setLastName(form.getLastName2());
            e.setSalary(salary);
            employeeRepository.save(e);

        }

        return "employee-updated";
    }


}
