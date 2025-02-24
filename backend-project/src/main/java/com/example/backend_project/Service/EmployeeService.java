package com.example.backend_project.Service;

import com.example.backend_project.DTO.EmployeeDTO;
import com.example.backend_project.Entity.Employee;
import com.example.backend_project.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long employeeId,EmployeeDTO updatedEmployee);

    void deleteEmployeeById(Long employeeId);
}
