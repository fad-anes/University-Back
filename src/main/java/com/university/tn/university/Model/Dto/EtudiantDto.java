package com.university.tn.university.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtudiantDto implements Serializable {
    private Long idetudiant;
    private String nom;
    private String prenom;
    private Long cin;
    private String ecole;
    private Date datenaissance;
    private Set<Reservation> reservations;
}
