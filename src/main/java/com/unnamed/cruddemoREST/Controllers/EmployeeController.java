package com.unnamed.cruddemoREST.Controllers;

import com.unnamed.cruddemoREST.DAOs.EmployeeDAO;
import com.unnamed.cruddemoREST.Entities.Employee;
import com.unnamed.cruddemoREST.Models.City;
import com.unnamed.cruddemoREST.Models.CustomResponseModel;
import com.unnamed.cruddemoREST.Service.EmployeeServiceJPA;
import jakarta.websocket.server.PathParam;
import jdk.jshell.Snippet;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;
    private final EmployeeServiceJPA employeeServiceJPA;

    public EmployeeController(EmployeeDAO employeeDAO, EmployeeServiceJPA employeeServiceJPA) {
        this.employeeDAO = employeeDAO;
        this.employeeServiceJPA = employeeServiceJPA;
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/testREST")
    public Object GetAllCities() {

        String uri = "https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8";
        RestTemplate restTemplate = new RestTemplate();

        Object res = restTemplate.getForObject(uri, City[].class);

        return res;
    }

    @GetMapping("/employees")
    public ResponseEntity<CustomResponseModel> GetEmployees() {

        var employees2 = employeeServiceJPA.findAll();
        var responseEntity = new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employees2, System.currentTimeMillis()), HttpStatus.OK);

        return responseEntity;

    }

    @GetMapping("/employees/pages/{pagina}/{cantidad}")
    public ResponseEntity<CustomResponseModel> GetEmployeesPaginated(@PathVariable("pagina") int pagina, @PathVariable("cantidad") int cantidad, @RequestParam LinkedHashMap<String, String> variosParametros) {

        var employees2 = employeeServiceJPA.findAllPaginated(pagina, cantidad);
        var responseEntity = new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employees2, System.currentTimeMillis()), HttpStatus.OK);

        return responseEntity;

    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<CustomResponseModel> GetById(@PathVariable int employeeId) {
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employeeDAO.findById(employeeId), System.currentTimeMillis()), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<CustomResponseModel> addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee a = new Employee(0, "aa", "aa", "aa");
        var aux = employeeDAO.addUpdateEmployee(employee);
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), aux, System.currentTimeMillis()), HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<CustomResponseModel> updateEmployee(@RequestBody Employee employee) {
        var aux = employeeDAO.addUpdateEmployee(employee);
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), aux, System.currentTimeMillis()), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<CustomResponseModel> deleteById(@PathVariable int employeeId) {
        return new ResponseEntity<CustomResponseModel>(new CustomResponseModel(HttpStatus.OK.value(), employeeDAO.deleteById(employeeId), System.currentTimeMillis()), HttpStatus.OK);
    }

}
