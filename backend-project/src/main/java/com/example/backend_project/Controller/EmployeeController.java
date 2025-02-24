package com.example.backend_project.Controller;

import com.example.backend_project.DTO.EmployeeDTO;
import com.example.backend_project.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    private final HandlerMapping resourceHandlerMapping;
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee =employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeID){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeID);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeDTOS  = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updateEmployeeDTO = employeeService.updateEmployee(employeeId,employeeDTO);
        return ResponseEntity.ok(updateEmployeeDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Deleted successfully");
    }

}
