package com.university.tn.university.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.university.tn.university.Model.Entity.Reservation;
import com.university.tn.university.Repository.ReservationRepository;
import java.util.List;
import java.util.Date;

@Service
public class ReservationServiceImpl implements IReservationService{
    @Autowired
    private ReservationRepository resrvationrepo;
    @Override
    public List<Reservation> retrieveAllReservation(){
        return  resrvationrepo.findAll();
    }
    @Override
    public Reservation addReservation(Reservation r){
        return  resrvationrepo.save(r);
    }
    @Override
    public Reservation updateReservation(Reservation r){
        return resrvationrepo.save(r);
    }
    @Override
    public Reservation retrieveReservation(String idreservation){
        return  resrvationrepo.getReferenceById(idreservation);
    }
    @Override
    public void removeReservation(String idreservation){
        resrvationrepo.deleteById(idreservation);
    }
    @Override
    public List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut,Date dateFin){
        return resrvationrepo.findByAnneeuniversitaireGreaterThanEqualAndAnneeuniversitaireLessThanEqual(dateDebut,dateFin);
    }

}
