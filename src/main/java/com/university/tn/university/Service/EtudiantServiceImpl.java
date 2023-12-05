package com.university.tn.university.Service;


import com.university.tn.university.Model.Entity.Etudiant;
import com.university.tn.university.Repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {
    EtudiantRepository etudiantRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(long idetudiant) {
        return etudiantRepository.findById(idetudiant).get();
    }

    @Override
    public void removeEtudiant(long idetudiant) {
        etudiantRepository.deleteById(idetudiant);
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {return etudiantRepository.saveAll(etudiants);}



}
