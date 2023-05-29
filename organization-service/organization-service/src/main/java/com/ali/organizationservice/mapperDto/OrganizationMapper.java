package com.ali.organizationservice.mapperDto;

import com.ali.organizationservice.dto.OrganizationDto;
import com.ali.organizationservice.entity.Organization;

public class OrganizationMapper {

    // JPA Entity to Dto
    public static OrganizationDto organizationMappingDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
        return organizationDto;
    }

    // Dto to JPA Entity
    public static Organization organizationMappingEntity(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate()
        );
        return organization;
    }
}
