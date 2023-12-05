package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    public List<Reservation> retrieveAllReservations();

    Reservation addReservation (Reservation r);

    Reservation updateReservation (Reservation r);

    Reservation retrieveReservation (String idreservation);

    void removeReservation  (String idreservation );

    List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut, Date dateFin);


}
