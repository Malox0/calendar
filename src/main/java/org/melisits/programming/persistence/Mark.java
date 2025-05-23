package org.melisits.programming.persistence;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marks")
@Data
@NoArgsConstructor
public class Mark {

    @Id
    @Column(name = "m_id")
    private Long id;

    @Column(name = "m_color", nullable = false)
    private Integer color;

    @Column(name = "m_text", length = 100, nullable = false)
    private String text;
}
