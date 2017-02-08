package com.nn.frontline.service;

import com.nn.frontline.model.Department;
import com.nn.frontline.model.Employee;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
public interface TestService {

    int deleteDepartment();

    List<Department> listDepartment();

    void createDepartment(Department newDepartment);

    Department findDepartment(Long id);

    Department updateDepartment(Department department);


    int deleteEmployee();

    List<Employee> listEmployee();

    void createEmployee(Employee newEmployee);

    Employee findEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
