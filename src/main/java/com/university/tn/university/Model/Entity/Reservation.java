package com.university.tn.university.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table( name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @Column(name="idreservation")
    private String idreservation;
    private Boolean estvalide;
    @Temporal(TemporalType.DATE)
    private Date anneeuniversitaire;
    @ManyToMany(mappedBy="reservations",cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<Etudiant> etudiants;


}
