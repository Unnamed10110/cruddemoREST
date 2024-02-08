package com.unnamed.cruddemoREST.InterfaceDAOs;

import com.unnamed.cruddemoREST.Entities.Employee;

import java.util.List;

public interface EmployeeDAOInterface {

    List<Employee> findAll();

    Employee findById(int id);
}
