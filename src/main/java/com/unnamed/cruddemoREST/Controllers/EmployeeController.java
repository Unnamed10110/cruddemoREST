package com.unnamed.cruddemoREST.Controllers;

import com.unnamed.cruddemoREST.DAOs.EmployeeDAO;
import com.unnamed.cruddemoREST.Models.CustomResponseModel;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
