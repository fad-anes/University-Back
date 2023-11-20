package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByBlocIdblocAndTypec(Long id , String DOUBLE);

    List<Chambre> findByReservationsEstvalide(Boolean a);

    List<Chambre> findByBlocNombloc(String nomBloc);
    List<Chambre> findByBlocIdblocAndBlocCapaciteblocGreaterThan(Long id ,Long cap);

    Optional<Chambre> findByNumeroChambre(Long i);

    @Query("SELECT c FROM Chambre c WHERE c.typec = :type AND c.bloc.nombloc = :nom")
    List<Chambre> RetrieveChambreByTypeAndBloc(@Param("type") String type, @Param("nom") String nom);

    @Query("SELECT c FROM Chambre c WHERE NOT EXISTS (SELECT r FROM c.reservations r WHERE r.estvalide = false)")
    List<Chambre> RetrieveChambreByValidationReservation(@Param("estValide") Boolean estValide);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.nombloc = :nom AND c.bloc.capacitebloc > :capacite")
    List<Chambre> RetrieveChambresByBlocAndCapacite(@Param("nom") String nom, @Param("capacite") Long capacite);

}
