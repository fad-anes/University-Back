package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Etudiant;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    Etudiant retrieveEtudiant(long idetudiant);
    void removeEtudiant(long idetudiant);
    List<Etudiant> addEtudiants (List<Etudiant> etudiants);

}
