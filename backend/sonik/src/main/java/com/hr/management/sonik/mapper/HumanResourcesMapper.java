package com.hr.management.sonik.mapper;

import com.hr.management.sonik.dto.HumanResourcesDto;
import com.hr.management.sonik.entity.HumanResources;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HumanResourcesMapper {
    
    public static HumanResourcesDto toHumanResourcesDto(HumanResources humanResources) {
        return HumanResourcesDto.builder()
                .id(humanResources.getId())
                .email(humanResources.getEmail())
                .firstName(humanResources.getFirstName())
                .lastName(humanResources.getLastName())
                .gender(humanResources.getGender())
                .birthdate(humanResources.getBirthdate())
                .tckn(humanResources.getTckn())
                .address(humanResources.getAddress())
                .userRole(humanResources.getUserRole())
                .isActive(humanResources.getIsActive())
                .creationDate(humanResources.getCreationDate())
                .candidates(humanResources.getCandidates())
                .build();
    }

    public static List<HumanResourcesDto> toHumanResourcesDto(List<HumanResources> humanResources) {
        return humanResources.stream().map(HumanResourcesMapper::toHumanResourcesDto).collect(Collectors.toList());
    }

}
