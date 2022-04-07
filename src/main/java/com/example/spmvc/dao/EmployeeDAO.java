package com.example.spmvc.dao;

import com.example.spmvc.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> listEmployee();
    public List<Employee> listEmployee(int page);
    public void createEpl(Employee employee);
    public Employee getEplById(int id);
    public void updateEpl(Employee employee);
    public void deleteEpl(int id);
    public List<Employee> searchEpl(String name);
}
