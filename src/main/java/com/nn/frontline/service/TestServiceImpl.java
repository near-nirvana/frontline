package com.nn.frontline.service;

import com.nn.frontline.model.Department;
import com.nn.frontline.model.Employee;
import com.nn.frontline.repo.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public int deleteDepartment() {
        return testDao.deleteDepartments();
    }

    @Override
    public List<Department> listDepartment() {
        return testDao.listDepartment();
    }

    @Override
    public void createDepartment(final Department newDepartment) {
        testDao.saveDepartment(newDepartment);
    }

    @Override
    public Department findDepartment(final Long id) {
        return testDao.findDepartment(id);
    }

    @Override
    public Department updateDepartment(final Department department) {
        return testDao.updateDepartment(department);
    }

    @Override
    public int deleteEmployee() {
        return testDao.deleteEmployees();
    }

    @Override
    public List<Employee> listEmployee() {
        return testDao.listEmployee();
    }

    @Override
    public void createEmployee(final Employee newEmployee) {
        testDao.saveEmployee(newEmployee);
    }

    @Override
    public Employee findEmployee(final Long id) {
        return testDao.findEmployee(id);
    }

    @Override
    public Employee updateEmployee(final Employee employee) {
        return testDao.updateEmployee(employee);
    }
}
