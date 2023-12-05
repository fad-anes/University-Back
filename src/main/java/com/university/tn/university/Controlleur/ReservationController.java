package com.university.tn.university.Controlleur;


import com.university.tn.university.Model.Entity.Reservation;
import com.university.tn.university.Service.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {
    IReservationService reservationService;

    // http://localhost:8000/university/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> retrieveAllReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }

    // http://localhost:8000/university/reservation/add-reservation
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    // http://localhost:8000/university/reservation/update-reservation
    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.updateReservation(r);
        return reservation;
    }

    // http://localhost:8000/university/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{id-reservation}")
    public Reservation retrieveReservation(@PathVariable("id-reservation") String idreservation) {
        return reservationService.retrieveReservation(idreservation);
    }

    // http://localhost:8000/university/reservation/remove-reservation/1
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String reservationid) {
        reservationService.removeReservation(reservationid);
    }


    // http://localhost:8000/university/reservation/get-reservations-by-annee-universitaire
    @GetMapping("/get-reservations-by-annee-universitaire")
    public List<Reservation> getReservationsByAcademicYear(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        return reservationService.getReservationParAnneeUniversitaire(dateDebut, dateFin);
    }


}
