package com.ali.departmentservice.service.impl;

import com.ali.departmentservice.dto.DepartmentDto;
import com.ali.departmentservice.entity.Department;
import com.ali.departmentservice.mapperDto.MappingDto;
import com.ali.departmentservice.repository.DepartmentRepository;
import com.ali.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // Dto to JPA Entity
        Department department = MappingDto.mappingEntity(departmentDto);
        // Save Department
        Department savedDepartment = departmentRepository.save(department);
        // JPA Entity to Dto
        DepartmentDto departmentDtoReturn = MappingDto.mappingDto(savedDepartment);

        return departmentDtoReturn;
    }
}
