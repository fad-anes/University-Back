package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Foyer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterfaceFoyerService {
    List<Foyer> retrieveAllFoyers();
    ResponseEntity<Foyer> addFoyer (Foyer f);
    ResponseEntity<Foyer> updateFoyer (Foyer f);
    Foyer retrieveFoyer (long idFoyer);
    void archiverFoyer (long idFoyer);
    ResponseEntity<Foyer> addFoyerWithBloc (Foyer foyer);
}
