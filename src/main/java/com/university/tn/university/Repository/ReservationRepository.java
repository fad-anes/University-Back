package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByAnneeuniversitaireGreaterThanEqualAndAnneeuniversitaireLessThanEqual(Date dateDebut, Date dateFin);
}
