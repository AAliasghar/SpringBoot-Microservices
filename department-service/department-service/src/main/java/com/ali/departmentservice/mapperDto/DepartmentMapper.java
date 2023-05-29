package com.ali.departmentservice.mapperDto;

import com.ali.departmentservice.dto.DepartmentDto;
import com.ali.departmentservice.entity.Department;

public class DepartmentMapper {
    // JPA Entity to Dto
    public static   DepartmentDto departmentMappingDto(Department department){

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }

    // Dto to JPA Entity
    public static   Department departmentMappingEntity(DepartmentDto departmentDto){

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }
}
