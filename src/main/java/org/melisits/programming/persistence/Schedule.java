package org.melisits.programming.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
public class Schedule {

    @Id
    @Column(name = "sc_id")
    private Long id;

    @Column(name = "sc_from", nullable = false)
    private LocalDate from;

    @Column(name = "sc_until", nullable = false)
    private LocalDate until;

    @Column(name = "sc_from_time", nullable = false)
    private LocalTime fromTime;

    @Column(name = "sc_until_time", nullable = false)
    private LocalTime untilTime;

    @Column(name = "sc_allday", nullable = false)
    private boolean allDay;

    @Column(name = "sc_title", length = 250, nullable = false)
    private String title;

    @Column(name = "sc_description", length = 500, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sc_a_id", referencedColumnName = "a_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sc_m_id", referencedColumnName = "m_id", nullable = false)
    private Mark mark;
}
