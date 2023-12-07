package com.university.tn.university.Controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.university.tn.university.Model.Entity.Reservation;
import com.university.tn.university.Service.ReservationServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationControlleur {

    @Autowired
    private ReservationServiceImpl ReservationService;
    public ReservationControlleur(ReservationServiceImpl ReservationServiceImpl){
        super();
        this.ReservationService=ReservationServiceImpl;
    }

    @GetMapping("/AllReservation")
    public List<Reservation> retrieveAllReservations() {return ReservationService.retrieveAllReservation();
    }

    @PostMapping("/AddReservation")
    public Reservation AddReservation(@RequestBody Reservation e){
        return ReservationService.addReservation(e);
    }

    @PutMapping("/UpdateReservation")
    public Reservation UpdateReservation(@RequestBody Reservation e){
        return ReservationService.updateReservation(e);
    }

    @GetMapping("/OneReservation/{id}")
    public Reservation OneReservation(@PathVariable("id") String id) {
        return ReservationService.retrieveReservation(id);
    }

    @DeleteMapping("/DeleteReservation/{id}")
    public void DeleteReservation(@PathVariable("id") String id) {
        ReservationService.removeReservation(id);
    }

    @PutMapping("/getReservationParAnneeUniversitaire/{dateFrom}/{dateTo}")
    public List<Reservation> getReservationParAnneeUniversitaire(@PathVariable("dateFrom") String  dateFromString,@PathVariable("dateTo") String  dateToString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom;
        Date dateTo;

        try {
            dateFrom = dateFormat.parse(dateFromString);
            dateTo = dateFormat.parse(dateToString);
        } catch (ParseException e) {

            throw new IllegalArgumentException("Format de date invalide. Utilisez le format 'yyyy-MM-dd'.", e);
        }
        return ReservationService.getReservationParAnneeUniversitaire(dateFrom,dateTo);
    }
}
