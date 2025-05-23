package org.melisits.programming.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "a_id", length = 255)
    private String id;

    @Column(name = "a_firstname", length = 255)
    private String firstName;

    @Column(name = "a_lastname", length = 255)
    private String lastName;

    @Column(name = "a_street", length = 255)
    private String street;

    @Column(name = "a_supplement", length = 255)
    private String supplement;

    @Column(name = "a_info", length = 255)
    private String info;

    @Column(name = "sc_a_id", length = 255)
    private String scAId;

    @Column(name = "a_l_id")
    private Integer aLId;

    @Column(name = "a_s_id")
    private Long aSId;
}
