package com.university.tn.university.Model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.university.tn.university.Model.Entity.Bloc;

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
    private boolean archived;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "foyer")
    @JsonIgnore
    private University university;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="foyer")
    private Set<Bloc> blocs;

    public boolean getArchived() {
        return archived;
    }
}