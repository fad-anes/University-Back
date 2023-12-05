package com.university.tn.university.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.university.tn.university.Model.Enum.TypeChambre;
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
@Table( name = "Chambre")
public class Chambre implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idchambre")
    private Long idchambre;
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typec;
    @JsonIgnore
    @ManyToOne
    private Bloc bloc;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
}
