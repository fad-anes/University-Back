package com.university.tn.university.Service;
 import com.university.tn.university.Model.Entity.Etudiant;

 import java.util.List;
 import  java.util.Date;
public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);
    List<Etudiant> addEtudiants (List<Etudiant> etudiants);
    Etudiant affecterEtudiantAReservation(String nomEt , String prenomEt , String idReservation );
    List<Etudiant> searchAndFilterEtudiants(String nom, String prenom, Long cin, String ecole, Date datenaissance, int page, int size
    );
}
