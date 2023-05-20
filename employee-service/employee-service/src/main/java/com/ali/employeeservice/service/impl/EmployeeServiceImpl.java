package com.ali.employeeservice.service.impl;

import com.ali.employeeservice.dto.APIResponseDto;
import com.ali.employeeservice.dto.DepartmentDto;
import com.ali.employeeservice.dto.EmployeeDto;
import com.ali.employeeservice.entity.Employee;
import com.ali.employeeservice.mapperDto.MappingDto;
import com.ali.employeeservice.repository.EmployeeRepository;
import com.ali.employeeservice.service.APIClient;
import com.ali.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;
//    private WebClient webClient;

    private APIClient apiClient;

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

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto  = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();

        // Getting Employee Department Code from Department
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = MappingDto.employeeMappingDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setEmployee(employeeDto);

        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }


}
