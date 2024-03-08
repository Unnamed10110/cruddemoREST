package com.unnamed.cruddemoREST.SpringDataJPA;

import com.unnamed.cruddemoREST.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "members") // personaliza el nombre base de la entidad en los endpoints / url
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
