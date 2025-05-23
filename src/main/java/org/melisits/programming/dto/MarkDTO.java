package org.melisits.programming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarkDTO {
    private Long id;
    private Integer color;
    private String text;
}
