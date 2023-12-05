package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Entity.Etudiant;
import com.university.tn.university.Service.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    IEtudiantService etudiantService;
    // http://localhost:8000/university/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> retrieveAllEtudiants(){
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    // http://localhost:8000/university/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant (@RequestBody Etudiant e){
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }

    // http://localhost:8000/university/etudiant/update-etudiant
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant (@RequestBody Etudiant e){
        Etudiant etudiant= etudiantService.updateEtudiant(e);
        return etudiant;
    }

    // http://localhost:8000/university/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") long etudiantid){
        return etudiantService.retrieveEtudiant(etudiantid);
    }

    // http://localhost:8000/university/etudiant/remove-etudiant/1
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") long etudiantid){
        etudiantService.removeEtudiant(etudiantid);
    }

    // http://localhost:8000/university/etudiant/add-etudiants
    @PostMapping("/add-etudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants) {
        List<Etudiant> addedEtudiants = etudiantService.addEtudiants(etudiants);
        return addedEtudiants;
    }

}
