package com.ali.employeeservice.service.impl;

import com.ali.employeeservice.dto.APIResponseDto;
import com.ali.employeeservice.dto.DepartmentDto;
import com.ali.employeeservice.dto.EmployeeDto;
import com.ali.employeeservice.entity.Employee;
import com.ali.employeeservice.mapperDto.MappingDto;
import com.ali.employeeservice.repository.EmployeeRepository;
import com.ali.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

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
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http;//localhost:8080/api/departments" + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto = MappingDto.employeeMappingDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setEmployeeDto(employeeDto);

        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }


}
