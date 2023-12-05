package com.university.tn.university.Model.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table( name = "Reservation")
public class Reservation implements Serializable{
    @Id
    @Column(name="idreservation")
    private String idreservation;
    private Boolean estvalide;
    @Temporal(TemporalType.DATE)
    private Date anneeuniversitaire;
    @ManyToMany(mappedBy="reservations",cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;
}
