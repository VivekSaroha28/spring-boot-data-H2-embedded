package com.iamvickyav.springboot.SpringBootRestWithH2;

import com.iamvickyav.springboot.SpringBootRestWithH2.controllerAdvice.EmployeeException;
import com.iamvickyav.springboot.SpringBootRestWithH2.model.Employee;
import com.iamvickyav.springboot.SpringBootRestWithH2.model.Product;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.EmployeeService;
import com.iamvickyav.springboot.SpringBootRestWithH2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee-service")
public class EmployeeController
{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeService employeeService2;

    @Autowired
    private ProductService productService;

    // VS : Product
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product getProduct(@PathVariable int id)
    {
        return productService.findById(id).get();
    }

    // Select, Insert, Delete, Update Operations for an Employee

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    ResponseEntity<Employee> getEmployee(@PathVariable Integer id)  // We can use ResponseEntity<Void> as well.
    {
        Employee emp = employeeService2.findById(id).get();
        if (id == 2)
        {
            try
            {
                int a = 10 / 0;
            } catch (Exception ex)
            {
                throw new EmployeeException("Employee doesn't exist", ex); // Here ex will be cause of EmployeeException
            }
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    // -----------------------------------------------------------------------------------------//

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee employee)
    {
        Employee savedEmployee = employeeService.save(employee);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    Employee updateEmployee(@RequestBody Employee employee)
    {
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    Map<String, String> deleteEmployee(@RequestParam Integer id)
    {
        Map<String, String> status = new HashMap<>();
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent())
        {
            employeeService.delete(employee.get());
            status.put("Status", "Employee deleted successfully");
        }
        else
        {
            status.put("Status", "Employee not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Employees

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    List<Employee> getAllEmployee()
    {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<Employee> employeeList)
    {
        employeeService.saveAll(employeeList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    String addAllEmployees()
    {
        employeeService.deleteAll();
        return "SUCCESS";
    }
}
