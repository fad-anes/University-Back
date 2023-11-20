package com.university.tn.university.Model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table( name = "University")
public class University implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="iduniverste")
    private Long iduniverste;
    private String nomuniverste;
    private String adresse;
    @OneToOne(fetch = FetchType.EAGER)
    private Foyer foyer;
    @OneToOne(mappedBy = "university")
    private User user;
}
