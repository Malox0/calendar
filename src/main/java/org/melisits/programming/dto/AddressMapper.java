package org.melisits.programming.mapper;

import org.melisits.programming.dto.AddressDTO;
import org.melisits.programming.persistence.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address entity) {
        if (entity == null) return null;
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setStreet(entity.getStreet());
        dto.setSupplement(entity.getSupplement());
        dto.setInfo(entity.getInfo());
        dto.setScAId(entity.getScAId());
        dto.setALId(entity.getALId());
        dto.setASId(entity.getASId());
        return dto;
    }

    public static Address toEntity(AddressDTO dto) {
        if (dto == null) return null;
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setStreet(dto.getStreet());
        entity.setSupplement(dto.getSupplement());
        entity.setInfo(dto.getInfo());
        entity.setScAId(dto.getScAId());
        entity.setALId(dto.getALId());
        entity.setASId(dto.getASId());
        return entity;
    }
}
