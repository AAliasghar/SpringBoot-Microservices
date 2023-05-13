package com.ali.departmentservice.service;

import com.ali.departmentservice.dto.DepartmentDto;


public interface DepartmentService {
    DepartmentDto saveDepartment( DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String DepartmentCode);
}
