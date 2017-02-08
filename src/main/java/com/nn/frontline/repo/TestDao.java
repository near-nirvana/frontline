package com.nn.frontline.repo;

import com.nn.frontline.model.Department;
import com.nn.frontline.model.Employee;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@SuppressWarnings("PMD")
public interface TestDao {

    Long save(Department department);

    int deleteDepartments();

    List<Department> listDepartment();

    void saveDepartment(Department newDepartment);

    Department findDepartment(Long id);

    Department updateDepartment(Department department);


    Long save(Employee employee);

    int deleteEmployees();

    List<Employee> listEmployee();

    void saveEmployee(Employee newEmployee);

    Employee findEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
