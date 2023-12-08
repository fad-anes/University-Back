package com.university.tn.university.Controlleur;

import com.university.tn.university.Service.EtudiantServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.university.tn.university.Model.Entity.Etudiant;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/searchAndFilter")
    public ResponseEntity<List<Etudiant>> searchAndFilterEtudiants(
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "prenom", required = false) String prenom,
            @RequestParam(name = "cin", required = false) Long cin,
            @RequestParam(name = "ecole", required = false) String ecole,
            @RequestParam(name = "datenaissance", required = false) Date datenaissance,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<Etudiant> etudiants = EtudiantService.searchAndFilterEtudiants(nom, prenom, cin, ecole, datenaissance, page, size);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }
}
