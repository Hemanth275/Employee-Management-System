package com.example.backend_project.Mapper;

import com.example.backend_project.DTO.EmployeeDTO;
import com.example.backend_project.Entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return new EmployeeDTO(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
    public static Employee mapToEmployee(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );
    }
}
