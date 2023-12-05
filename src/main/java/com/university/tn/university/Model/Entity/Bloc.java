package com.university.tn.university.Model.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table( name = "Bloc")
public class Bloc implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idbloc")
    private Long idbloc;
    private String nombloc;
    private Long capacitebloc;
    @ManyToOne
    private Foyer foyer;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="bloc")
    private Set<Chambre> chambres;
}
