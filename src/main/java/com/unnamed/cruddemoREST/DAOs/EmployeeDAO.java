package com.unnamed.cruddemoREST.DAOs;

import com.unnamed.cruddemoREST.Entities.Employee;
import com.unnamed.cruddemoREST.InterfaceDAOs.EmployeeDAOInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO implements EmployeeDAOInterface {

    private final EntityManager entityManager;

    @Autowired  // inyeccion de el servicio
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        String queryString = "FROM Employee a ORDER BY a.lastName";

        TypedQuery<Employee> res = entityManager.createQuery(queryString, Employee.class);
        var res2=res.getResultList();

        return res2;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

}
