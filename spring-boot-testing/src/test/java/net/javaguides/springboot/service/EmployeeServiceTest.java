package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

import net.javaguides.springboot.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;


    @Test
    public void given_when_then(){
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);

        System.out.println(employeeServiceImpl);

        Employee employee1 = employeeServiceImpl.saveEmployee(employee);

        Assertions.assertThat(employee1).isNotNull();

    }

    @Test
    public void givenEmployeeObj_whenFindAll_thenReturnListOfEmployees(){

        Employee employee= Employee.builder()
                .id(1L)
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();
        Employee employee1= Employee.builder()
                .id(2L)
                .firstName("krishna")
                .lastName("radha")
                .email("krishna@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();

        Assertions.assertThat(allEmployees.size()).isEqualTo(2);
        Assertions.assertThat(allEmployees).isNotNull();

    }

    @Test
    public  void givenEmployeeId_whenFindById_theReturnEmployeeObJ(){
        Employee employee= Employee.builder()
                .id(1L)
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        Employee employee1 = employeeServiceImpl.getEmployeeById(employee.getId()).get();

        Assertions.assertThat(employee1).isNotNull();
    }

    @Test
    public  void givenEmployeeObj_whenSave_theReturnEmployeeUpdatedObJ(){
        Employee employee= Employee.builder()
                .id(1L)
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();


        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        employee.setFirstName("krish");
        employee.setLastName("roshan");

        Employee employee1 = employeeServiceImpl.updateEmployee(employee);

        Assertions.assertThat(employee1.getFirstName()).isEqualTo("krish");
    }

    @Test
    public  void givenEmployeeId_whenDelete_thenDeleteEmployee(){
        Employee employee= Employee.builder()
                .id(1L)
                .firstName("ram")
                .lastName("sita")
                .email("ram@gmail.com")
                .build();


      // BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        BDDMockito.willDoNothing().given(employeeRepository).deleteById(1L);

       employeeServiceImpl.deleteEmployee(1L);

        BDDMockito.verify(employeeRepository, Mockito.times(1)).deleteById(1L);
    }
}