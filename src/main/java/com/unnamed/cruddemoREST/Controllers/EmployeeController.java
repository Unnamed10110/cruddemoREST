package com.unnamed.cruddemoREST.Controllers;

import com.unnamed.cruddemoREST.DAOs.EmployeeDAO;
import com.unnamed.cruddemoREST.Entities.Employee;
import com.unnamed.cruddemoREST.Models.CustomResponseModel;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employees")
    public ResponseEntity<CustomResponseModel> GetEmployees(){
        var employees=employeeDAO.findAll();

        var responseEntity=new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employees, System.currentTimeMillis()),HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<CustomResponseModel> GetById(@PathVariable int employeeId){
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employeeDAO.findById(employeeId),System.currentTimeMillis()),HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<CustomResponseModel> addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee a=new Employee(0,"aa","aa","aa");
        var aux=employeeDAO.addUpdateEmployee(employee);
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(),aux,System.currentTimeMillis()),HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<CustomResponseModel> updateEmployee(@RequestBody Employee employee){
        var aux=employeeDAO.addUpdateEmployee(employee);
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(),aux,System.currentTimeMillis()),HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<CustomResponseModel> deleteById(@PathVariable int employeeId){
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employeeDAO.deleteById(employeeId),System.currentTimeMillis()),HttpStatus.OK);
    }

}
