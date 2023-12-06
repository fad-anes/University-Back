package com.university.tn.university.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Table( name = "Etudiant")
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idetudiant")
    private Long idetudiant;
    private String nom;
    private String prenom;
    private Long cin;
    private String ecole;
    @Temporal(TemporalType.DATE)
    private Date datenaissance;
    @ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<Reservation> reservations;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "etudiant")
    private User User;
}
