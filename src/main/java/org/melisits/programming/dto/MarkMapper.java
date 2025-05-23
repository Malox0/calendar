package org.melisits.programming.mapper;

import org.melisits.programming.dto.MarkDTO;
import org.melisits.programming.persistence.Mark;

public class MarkMapper {

    public static MarkDTO toDTO(Mark entity) {
        if (entity == null) return null;
        MarkDTO dto = new MarkDTO();
        dto.setId(entity.getId());
        dto.setColor(entity.getColor());
        dto.setText(entity.getText());
        return dto;
    }

    public static Mark toEntity(MarkDTO dto) {
        if (dto == null) return null;
        Mark entity = new Mark();
        entity.setId(dto.getId());
        entity.setColor(dto.getColor());
        entity.setText(dto.getText());
        return entity;
    }
}
