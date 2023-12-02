package com.university.tn.university.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto implements Serializable {
    private String idreservation;
    private Boolean estvalide;
    private Date anneeuniversitaire;
    private Set<Etudiant> etudiants;
}
