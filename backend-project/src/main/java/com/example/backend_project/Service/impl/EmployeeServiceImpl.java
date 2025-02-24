package com.example.backend_project.Service.impl;

import com.example.backend_project.DTO.EmployeeDTO;
import com.example.backend_project.Entity.Employee;
import com.example.backend_project.Exception.ResourceNotFoundException;
import com.example.backend_project.Mapper.EmployeeMapper;
import com.example.backend_project.Repository.EmployeeRepository;
import com.example.backend_project.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee not exists in the DB with given ID"));

        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee>employeeList = employeeRepository.findAll();
        return employeeList.stream().map((EmployeeMapper::mapToEmployeeDTO)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId,EmployeeDTO updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee not exists with given Id: "+employeeId)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with id+ "+employeeId));
        employeeRepository.deleteById(employeeId);
    }


}
