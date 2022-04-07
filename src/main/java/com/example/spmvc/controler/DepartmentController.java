package com.example.spmvc.controler;


import com.example.spmvc.dao.DepartmentDAO;
import com.example.spmvc.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(){

        return "home";
    }

    @RequestMapping({ "/deptList" })
    public String deptList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        //departmentDAO.createDepartment("Dept Name", "Dept Location");


        List<Department> list = departmentDAO.listDepartment(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("dept", list);
        return "deptlist";
    }

    @RequestMapping({"/detailDept/{id}"})
    public String deptDetail(@PathVariable int id, Model model){
        Department department = departmentDAO.getDeptById(id);

        model.addAttribute("dept", departmentDAO.listDepartment());
        model.addAttribute("detailDept", department);

        return "deptlist";
    }

    @RequestMapping(value = "/createDept", method = RequestMethod.POST)
    public String createDept(@ModelAttribute("Department") Department department, Model model){
        departmentDAO.createDept(department);
        model.addAttribute("dept", departmentDAO.listDepartment());

        return "deptlist";
    }

    @RequestMapping(value = "/updateDept", method = RequestMethod.POST)
    public String updateDept(@ModelAttribute("Department") Department department, Model model){
        departmentDAO.updateDept(department);
        model.addAttribute("dept", departmentDAO.listDepartment());

        return "deptlist";
    }

    @RequestMapping({"/deleteDept/{id}"})
    public String deleteDept(@PathVariable int id, Model model){

        departmentDAO.deleteDept(id);

        model.addAttribute("dept", departmentDAO.listDepartment());

        return "redirect:/deptList";
    }

}
