package com.ali.employeeservice.service;

import com.ali.employeeservice.dto.APIResponseDto;
import com.ali.employeeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto   getEmployeeById(Long employeeId);

}
