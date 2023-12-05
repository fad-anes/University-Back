package com.university.tn.university.Service;


import com.university.tn.university.Model.Entity.Reservation;
import com.university.tn.university.Repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation retrieveReservation(String idreservation) {
        return reservationRepository.findById(idreservation).get();
    }

    @Override
    public void removeReservation(String idreservation) {
        reservationRepository.deleteById(idreservation);
    }


    @Override
    public List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut, Date dateFin) {
        return reservationRepository.findByAnneeuniversitaireBetween(dateDebut, dateFin);
    }


}
