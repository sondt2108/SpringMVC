package com.example.spmvc.dao;

import com.example.spmvc.models.Department;

import java.util.List;

public interface DepartmentDAO {

    public List<Department> listDepartment();
    public List<Department> listDepartment(int pageNumber) ;
    public Department getDeptById(int id);
    public void createDept(Department department);
    public void updateDept(Department department);
    public void deleteDept(int id);
}