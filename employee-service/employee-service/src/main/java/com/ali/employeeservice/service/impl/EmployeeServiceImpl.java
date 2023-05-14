package com.ali.employeeservice.service.impl;

import com.ali.employeeservice.dto.EmployeeDto;
import com.ali.employeeservice.entity.Employee;
import com.ali.employeeservice.mapperDto.MappingDto;
import com.ali.employeeservice.repository.EmployeeRepository;
import com.ali.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // Save Employee
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        // Dto to JPA Entity
        Employee employee = MappingDto.employeeMappingEntity(employeeDto);
        // Save Employee
        Employee savedEmployee = employeeRepository.save(employee);
        // JPA Entity to Dto
        EmployeeDto employeeDtoReturn = MappingDto.employeeMappingDto(savedEmployee);

        return employeeDtoReturn;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto = MappingDto.employeeMappingDto(employee);
        return employeeDto;
    }


}
