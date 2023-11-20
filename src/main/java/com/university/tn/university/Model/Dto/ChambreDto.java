package com.university.tn.university.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Entity.Reservation;
import com.university.tn.university.Model.Enum.TypeChambre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChambreDto implements Serializable {
    private Long idchambre;
    private Long numeroChambre;
    private TypeChambre typec;
    private Bloc bloc;
    private Set<Reservation> reservations;
}
