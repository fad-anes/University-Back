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
@Table( name = "Foyer")
public class Foyer implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idfoyer")
    private Long idfoyer;
    private String nomfoyer;
    private Long capacitefoyer;
    private boolean archived = false;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "foyer")
    private University university;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="foyer")
    private Set<Bloc> blocs;
}
