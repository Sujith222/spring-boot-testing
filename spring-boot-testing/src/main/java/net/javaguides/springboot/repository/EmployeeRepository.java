package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//we can neglect this becoz jpaRepository internally has @Repository
//and all are marked with @Transactional


public interface EmployeeRepository extends JpaRepository<Employee,Long> {

        Optional<Employee> findByEmail(String email);


}
