package com.nn.frontline.resources;

import com.nn.frontline.model.Department;
import com.nn.frontline.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@RestController
public class DepartmentResources {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/departments", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteDepartments() {
        testService.deleteDepartment();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public List<Department> getDepartments() {
        return testService.listDepartment();
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Department postDepartment(@RequestBody final Department department) {
        final Department newDepartment = new Department.Builder()
                .withName(department.getName())
                .build();
        testService.createDepartment(newDepartment);
        return newDepartment;
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department department(@PathVariable("id") final  Long id) {
        return testService.findDepartment(id);
    }

    @RequestMapping(value = "/department", method = RequestMethod.PUT)
    public Department updateDepartment(@RequestBody final Department department) {

        return testService.updateDepartment(new Department.Builder()
                .withId(department.getId())
                .withName(department.getName())
                .build());
    }
}
