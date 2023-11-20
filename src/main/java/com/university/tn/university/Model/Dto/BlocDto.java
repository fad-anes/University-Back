package com.university.tn.university.Model.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Entity.Foyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlocDto implements Serializable {
    private Long idbloc;
    private String nombloc;
    private Long capacitebloc;
    private Foyer foyer;
    private Set<Chambre> chambres;
}
