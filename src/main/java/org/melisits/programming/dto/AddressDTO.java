package org.melisits.programming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String street;
    private String supplement;
    private String info;
    private String scAId;
    private Integer aLId;
    private Long aSId;
}
