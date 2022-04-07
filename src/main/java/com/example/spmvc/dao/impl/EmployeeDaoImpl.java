package com.example.spmvc.dao.impl;

import com.example.spmvc.dao.EmployeeDAO;
import com.example.spmvc.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class EmployeeDaoImpl implements EmployeeDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private static final int pageSize = 3;

    @SuppressWarnings("unchecked")
    public List<Employee> listEmployee(int pageNumber){
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> listEmployee = null;

        Query query = session.createQuery("from Employee ");
        query = query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        listEmployee = query.list();

        return listEmployee;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> listEmployee(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> listEmployees = session.createQuery("from Employee ").list();


        return listEmployees;
    }


    public void createEpl(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();

        session.save(employee);
    }

    public Employee getEplById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return session.get(Employee.class, id);
    }


    public void updateEpl(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(employee);
    }

    public void deleteEpl(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);
        if (employee != null){
            session.delete(employee);
        }
    }

    @Override
    public List<Employee> searchEpl(String name) {
        Session session = this.sessionFactory.getCurrentSession();

        List<Employee> listEmployee = null;

        Query query = session.createQuery("from Employee e where e.fullName like : name");

        query.setParameter("name", "%" + name +"%");

        listEmployee = query.list();

        return listEmployee;

    }
}
