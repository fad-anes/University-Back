package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Enum.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    public Chambre findByNumeroChambre(long numeroChambre);


    long countByTypecAndBlocIdbloc(TypeChambre typec, long idbloc);


    List<Chambre> findByBlocNombloc(String nombloc);

}
