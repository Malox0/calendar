package org.melisits.programming.dto;

import org.melisits.programming.dto.ScheduleDTO;
import org.melisits.programming.persistence.Schedule;

public class ScheduleMapper {

    public static ScheduleDTO toDTO(Schedule entity) {
        if (entity == null) return null;
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setScFrom(entity.getScFrom());
        dto.setScUntil(entity.getScUntil());
        dto.setFromTime(entity.getFromTime());
        dto.setUntilTime(entity.getUntilTime());
        dto.setAllDay(entity.isAllDay());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        // Address and Mark mapping
        dto.setAddress(org.melisits.programming.mapper.AddressMapper.toDTO(entity.getAddress()));
        dto.setMark(org.melisits.programming.mapper.MarkMapper.toDTO(entity.getMark()));

        return dto;
    }

    public static Schedule toEntity(ScheduleDTO dto) {
        if (dto == null) return null;
        Schedule entity = new Schedule();
        entity.setId(dto.getId());
        entity.setScFrom(dto.getScFrom());
        entity.setScUntil(dto.getScUntil());
        entity.setFromTime(dto.getFromTime());
        entity.setUntilTime(dto.getUntilTime());
        entity.setAllDay(dto.isAllDay());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        // Address and Mark mapping
        entity.setAddress(org.melisits.programming.mapper.AddressMapper.toEntity(dto.getAddress()));
        entity.setMark(org.melisits.programming.mapper.MarkMapper.toEntity(dto.getMark()));

        return entity;
    }
}
