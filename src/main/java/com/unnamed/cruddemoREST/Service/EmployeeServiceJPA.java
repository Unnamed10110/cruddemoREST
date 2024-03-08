package com.unnamed.cruddemoREST.Service;

import com.unnamed.cruddemoREST.Entities.Employee;
import com.unnamed.cruddemoREST.SpringDataJPA.EmployeeRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceJPA {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceJPA(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Page<Employee> findAllPaginated(int a, int b){
        Page<Employee> auxEmployee= employeeRepository.findAll(PageRequest.of(a,b));
        return auxEmployee;

    }


    public Employee findById(int id){
        Optional<Employee> result= employeeRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else{
            throw new RuntimeException("No employee with id :"+id);
        }
    }
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee delete(int id){
        var employee=employeeRepository.getById(id);
        var aux=employee;
        employeeRepository.deleteById(id);
        return aux;
    }

}
