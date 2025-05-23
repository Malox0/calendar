package org.melisits.programming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private LocalDate scFrom;
    private LocalDate scUntil;
    private LocalTime fromTime;
    private LocalTime untilTime;
    private boolean allDay;
    private String title;
    private String description;
    private AddressDTO address;
    private MarkDTO mark;
}
