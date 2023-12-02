package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {


}
