package com.example.spmvc.dao.impl;

import com.example.spmvc.dao.DepartmentDAO;
import com.example.spmvc.models.Department;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DepartmentDaoImpl implements DepartmentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private static final int pageSize = 3;

    @SuppressWarnings("unchecked")
    public List<Department> listDepartment(int pageNumber){
        Session session = this.sessionFactory.getCurrentSession();
        List<Department> listDept = null;

        Query query = session.createQuery("from Department ");
        query = query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        listDept = query.list();

        return listDept;
    }

    @SuppressWarnings("unchecked")
    public List<Department> listDepartment(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Department> listDept = session.createQuery("from Department ").list();


        return listDept;
    }


    public Department getDeptById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return  session.get(Department.class, id);
    }


    public void createDept(Department department) {
        Session session = this.sessionFactory.getCurrentSession();

        session.save(department);
    }

    public void updateDept(Department department) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(department);
    }


    public void deleteDept(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, id);
            if (department != null) {
                session.delete(department);
            }
    }
}
