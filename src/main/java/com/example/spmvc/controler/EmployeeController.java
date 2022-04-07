package com.example.spmvc.controler;


import com.example.spmvc.dao.DepartmentDAO;
import com.example.spmvc.dao.EmployeeDAO;
import com.example.spmvc.models.Department;
import com.example.spmvc.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping({ "/employeeList" })
    public String employeeList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        //departmentDAO.createDepartment("Dept Name", "Dept Location");


        List<Employee> list = employeeDAO.listEmployee(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("epl", list);
        model.addAttribute("dept", getDepartment());
        return "employeelist";
    }

    @ModelAttribute("departments")
    public List<Department> getDepartment(){
        List<Department> departmentList = departmentDAO.listDepartment();

        return departmentList;
    }

    @RequestMapping(value = "/createEpl", method = RequestMethod.POST)
    public String createEpl(@ModelAttribute("Employee") Employee employee ,Model model){


        employeeDAO.createEpl(employee);

        return "redirect:/employeeList";
    }

    @RequestMapping({"/detailEpl/{id}"})
    public String detailEpl(@PathVariable int id, Model model, @RequestParam(name = "page", defaultValue = "1") int page){
        Employee employee = employeeDAO.getEplById(id);

        model.addAttribute("detailEmployee", employee);
        model.addAttribute("epl", employeeDAO.listEmployee(page));
        model.addAttribute("dept", getDepartment());
        model.addAttribute("currentPage", page);

        return "employeelist";
    }

    @RequestMapping(value = "/updateEpl", method = RequestMethod.POST)
    public String updateEpl(@ModelAttribute("Employee") Employee employee, Model model){

        employeeDAO.updateEpl(employee);

        return "redirect:/employeeList";
    }

    @RequestMapping({"/deleteEpl/{id}"})
    public String deleteDept(@PathVariable int id, Model model, @RequestParam(name = "page", defaultValue = "1") int page){

        employeeDAO.deleteEpl(id);

        model.addAttribute("epl", employeeDAO.listEmployee(page));

        return "redirect:/employeeList";
    }


    @RequestMapping(value = "/searchEpl", method = RequestMethod.POST)
    public String searchEpl(@ModelAttribute("Employee") Employee employee, Model model){
        String name = employee.getFullName();

        System.out.println("-------------------------name:" +name);

        List<Employee> list = employeeDAO.searchEpl(name);

        model.addAttribute("epl", list);

        return "employeelist";
    }
}
