package com.ali.employeeservice.mapperDto;

import com.ali.employeeservice.dto.EmployeeDto;
import com.ali.employeeservice.entity.Employee;

public class EmployeeMapper {
    // JPA Entity to Dto
    public static EmployeeDto employeeMappingDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
        return employeeDto;
    }

    // Dto to JPA Entity
    public static Employee employeeMappingEntity(EmployeeDto employeeDto){
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
        return employee;
    }
}
