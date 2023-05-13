package com.ali.departmentservice.controller;

import com.ali.departmentservice.dto.DepartmentDto;
import com.ali.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
    DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
    return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }
}
