package com.university.tn.university.Controlleur;

import com.university.tn.university.Service.EtudiantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.university.tn.university.Model.Entity.Etudiant;

import java.util.List;

@RestController
public class EtudiantControlleur {
    @Autowired
    private EtudiantServiceImpl EtudiantService;

    public EtudiantControlleur(EtudiantServiceImpl Etudiantserviceimpl){
        super();
        this.EtudiantService=Etudiantserviceimpl;
    }

    @GetMapping("/AllStudent")
    public List<Etudiant> retrieveAllStudents() {
        return EtudiantService.retrieveAllEtudiants();
    }

    @PostMapping("/Addstudent")
    public Etudiant Addstudent(@RequestBody Etudiant e){
        return EtudiantService.addEtudiant(e);
    }

    @PutMapping("/Updatestudent")
    public Etudiant Updatestudent(@RequestBody Etudiant e){
        return EtudiantService.updateEtudiant(e);
    }

    @GetMapping("/OneStudent/{id}")
    public Etudiant OneStudent(@PathVariable("id") Long id) {
        return EtudiantService.retrieveEtudiant(id);
    }

    @DeleteMapping("/DeleteStudent/{id}")
    public void DeleteStudent(@PathVariable("id") Long id) {
        EtudiantService.removeEtudiant(id);
    }

    @PostMapping("/AddListstudent")
    public List<Etudiant> AddListstudent(@RequestBody List<Etudiant> etudiants){
        return EtudiantService.addEtudiants(etudiants);
    }

    @GetMapping("/affecterEtudiantAReservation/{nom}/{prenom}/{id}")
    public Etudiant affecterEtudiantAReservation(@PathVariable("nom") String nom,@PathVariable("prenom") String prenom,@PathVariable("id") String id) {
        return EtudiantService.affecterEtudiantAReservation(nom,prenom,id);
    }
}
