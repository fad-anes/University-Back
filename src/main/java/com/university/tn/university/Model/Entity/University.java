package com.university.tn.university.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table( name = "University")
public class University implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="iduniverste")
    private Long iduniverste;
    private String nomuniverste;
    private String adresse;
    @OneToOne(fetch = FetchType.EAGER)
    private Foyer foyer;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="university")
    //@JsonManagedReference
    private Set<User> users;
}
