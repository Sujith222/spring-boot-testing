package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName(value = "junit test for save one employee")
    public void givenEmployeeObj_whenSave_thenReturnSavedEmployee(){

        //given---->setup
        Employee employee = Employee.builder()
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();
        //when------>action
        Employee emp = employeeRepository.save(employee);

        //then------->verify the output
        assertThat(emp).isNotNull();
        assertThat(emp.getId()).isGreaterThan(0);
        assertThat(emp.getFirstName()).isEqualTo("ram");

    }

   @Test
   @DisplayName(value = "junit test for geting all the employee records")
   public void givenEmployeesList_whenFindAll_thenReturnListOfEmployees(){
        Employee employee= Employee.builder()
                .firstName("Hrithik")
                .lastName("Roshan")
                .email("hrithik@gmail.com")
                .build();
       Employee employee1= Employee.builder()
               .firstName("Krishna")
               .lastName("Radha")
               .email("Krishna@gmail.com")
               .build();
       Employee employee2= Employee.builder()
               .firstName("Hanuman")
               .lastName("Patti")
               .email("Hanuman@gmail.com")
               .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

       List<Employee> all = employeeRepository.findAll();
       assertThat(all).isNotNull();
       assertThat(all.size()).isEqualTo(3);
      // assertThat(all.get(1)).isEqualTo("Krishna");
   }

   @Test
   @DisplayName(value = "junit test for reading an employee by id")
    public void givenEmployeeId_whenFindById_thenReturnEmployeeById(){
       Employee employee= Employee.builder()
               .firstName("Hrithik")
               .lastName("Roshan")
               .email("hrithik@gmail.com")
               .build();
       employeeRepository.save(employee);
       Employee employee1 = employeeRepository.findById(employee.getId()).get();
       assertThat(employee1).isNotNull();
       //assertThat(employee1).isEqualTo(1L);
   }

   @Test
   public void givenEmployeeObj_whenUpdateById_thenReturnUpdatedRecord(){

        Employee employee = Employee.builder()
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();
        employeeRepository.save(employee);
       Employee savedEmployeeById= employeeRepository.findById(employee.getId()).get();
       savedEmployeeById.setFirstName("Hrithik");
       savedEmployeeById.setLastName("Roshan");
       savedEmployeeById.setEmail("hrithik@gmail.com");
       Employee updatedEmployee = employeeRepository.save(savedEmployeeById);
       assertThat(updatedEmployee).isNotNull();
       assertThat(updatedEmployee.getFirstName()).isEqualTo("Hrithik");


   }
    @Test
    @DisplayName(value = "junit test for deleting an employee by id")
    public void givenEmployeeId_whenDeleteById_thenReturnEmployeeByIdDeleted(){
        Employee employee= Employee.builder()
                .firstName("Hrithik")
                .lastName("Roshan")
                .email("hrithik@gmail.com")
                .build();
        employeeRepository.save(employee);


         employeeRepository.deleteById(employee.getId());

        Optional<Employee> byId = employeeRepository.findById(employee.getId());
        assertThat(byId).isEmpty();
        //assertThat(employee1).isNotNull();
        //assertThat(employee1).isEqualTo(1L);
    }
}