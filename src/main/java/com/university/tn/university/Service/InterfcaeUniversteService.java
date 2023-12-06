package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.University;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterfcaeUniversteService {
    List<University> retrieveAllUniversities();
    ResponseEntity<University> addUniversity (University u);
    ResponseEntity<University> updateUniversity (University u);
    University retrieveUniversity (long idUniversity);
    void removeUniversity  (long idUniversity );

    ResponseEntity<University> affecterFoyerAUniversite(long idFoyer, String nomUniversite) ;

    ResponseEntity<University> desaffecterFoyerAUniversite (long idFoyer,  long idUniversite);

}
