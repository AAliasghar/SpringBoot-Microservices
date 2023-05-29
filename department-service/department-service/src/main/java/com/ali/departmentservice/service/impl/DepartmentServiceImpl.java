package com.ali.departmentservice.service.impl;

import com.ali.departmentservice.dto.DepartmentDto;
import com.ali.departmentservice.entity.Department;
import com.ali.departmentservice.mapperDto.DepartmentMapper;
import com.ali.departmentservice.repository.DepartmentRepository;
import com.ali.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    // Save Department
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // Dto to JPA Entity
        Department department = DepartmentMapper.departmentMappingEntity(departmentDto);
        // Save Department
        Department savedDepartment = departmentRepository.save(department);
        // JPA Entity to Dto
        DepartmentDto departmentDtoReturn = DepartmentMapper.departmentMappingDto(savedDepartment);

        return departmentDtoReturn;
    }

    // Get Department by Code
    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = DepartmentMapper.departmentMappingDto(department);
        return departmentDto;
    }
}
