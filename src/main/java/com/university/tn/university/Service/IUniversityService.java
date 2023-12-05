package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.University;

import java.util.List;

public interface IUniversityService {
    List<University> retrieveAllUniversites();
    University addUniversite (University u);
    University updateUniversite (University u);
    University retrieveUniversite (long iduniversite);
    void removeUniversite  (long iduniversite );

    University affecterFoyerAUniversite (long idfoyer, String nomuniversite) ;

    University desaffecterFoyerAUniversite (long idfoyer, long iduniversite) ;

}
