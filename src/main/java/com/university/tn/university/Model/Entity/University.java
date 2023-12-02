package com.university.tn.university.Model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table( name = "University")
public class University implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="iduniverste")
    private Long iduniverste;
    private String nomuniverste;
    private String adresse;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Foyer foyer;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="university")
    @JsonIgnore
    private Set<User> users;

}
