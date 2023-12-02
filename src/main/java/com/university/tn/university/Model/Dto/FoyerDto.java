package com.university.tn.university.Model.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Entity.University;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoyerDto implements Serializable {
    private Long idfoyer;
    private String nomfoyer;
    private Long capacitefoyer;
    private boolean archived = false;
    private University university;
    private Set<Bloc> blocs;
}
