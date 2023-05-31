package com.ali.organizationservice.service.impl;

import com.ali.organizationservice.dto.OrganizationDto;
import com.ali.organizationservice.entity.Organization;
import com.ali.organizationservice.mapperDto.OrganizationMapper;
import com.ali.organizationservice.repository.OrganizationRepository;
import com.ali.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    // Save Organization
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        // Covert OrganizationDto to JPA Entity
        Organization organization = OrganizationMapper.organizationMappingEntity(organizationDto);

        // Save Organization
        Organization savedOrganization = organizationRepository.save(organization);

        // Covert JPA Entity to Dto
        OrganizationDto organizationDtoReturn = OrganizationMapper.organizationMappingDto(savedOrganization);

        return organizationDtoReturn;
    }

    // Get Organization

    public OrganizationDto getOrganizationByCode (String organizationCode){
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDto organizationDto = OrganizationMapper.organizationMappingDto(organization);

        return organizationDto;
    }
}
