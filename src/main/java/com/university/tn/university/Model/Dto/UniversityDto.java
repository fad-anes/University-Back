package com.university.tn.university.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Model.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UniversityDto implements Serializable {

    private Long iduniverste;
    private String nomuniverste;
    private String adresse;
    private Foyer foyer;
    private User user;
}
