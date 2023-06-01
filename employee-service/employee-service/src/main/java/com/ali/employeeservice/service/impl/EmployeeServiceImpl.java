package com.ali.employeeservice.service.impl;

import com.ali.employeeservice.dto.APIResponseDto;
import com.ali.employeeservice.dto.DepartmentDto;
import com.ali.employeeservice.dto.EmployeeDto;
import com.ali.employeeservice.dto.OrganizationDto;
import com.ali.employeeservice.entity.Employee;
import com.ali.employeeservice.mapperDto.EmployeeMapper;
import com.ali.employeeservice.repository.EmployeeRepository;
import com.ali.employeeservice.service.APIClient;
import com.ali.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

     private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;
    private WebClient webClient;

    private APIClient apiClient;

    // Save Employee
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        // Dto to JPA Entity
        Employee employee = EmployeeMapper.employeeMappingEntity(employeeDto);
        // Save Employee
        Employee savedEmployee = employeeRepository.save(employee);
        // JPA Entity to Dto
        EmployeeDto employeeDtoReturn = EmployeeMapper.employeeMappingDto(savedEmployee);

        return employeeDtoReturn;
    }

    //@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto  = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();

        // Getting Employee Department Code from Department
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        // Getting Employee Organization Code from Organization
//        OrganizationDto organizationDto = apiClient.getDepartment(employee.getOrganizationCode());

        OrganizationDto organizationDto  = webClient.get().uri("http://localhost:8083/api/organizations/"
                + employee.getOrganizationCode()).retrieve().bodyToMono(OrganizationDto.class).block();

        // JPA Entity to Dto
        EmployeeDto employeeDto = EmployeeMapper.employeeMappingDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setEmployee(employeeDto);

        apiResponseDto.setDepartment(departmentDto);

        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }


    // Getting Default information
    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        // JPA Entity to Dto
        EmployeeDto employeeDto = EmployeeMapper.employeeMappingDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setEmployee(employeeDto);

        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}
