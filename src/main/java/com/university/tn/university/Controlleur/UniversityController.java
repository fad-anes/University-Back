package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Entity.University;
import com.university.tn.university.Service.IUniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversityController {
    IUniversityService universiteService;

    // http://localhost:8000/university/university/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    public List<University> retrieveAllUniversite() {
        List<University> listUniversities = universiteService.retrieveAllUniversites();
        return listUniversities;
    }

    // http://localhost:8000/university/university/add-universite
    @PostMapping("/add-universite")
    public University addUniversite(@RequestBody University u) {
        University universite = universiteService.addUniversite(u);
        return universite;
    }

    // http://localhost:8000/university/university/update-universite
    @PutMapping("/update-universite")
    public University updateUniversite(@RequestBody University u) {
        University university = universiteService.updateUniversite(u);
        return university;
    }

    // http://localhost:8000/university/university/retrieve-universite/8
    @GetMapping("/retrieve-universite/{id-universite}")
    public University retrieveUniversite(@PathVariable("id-universite") long iduniversite) {
        return universiteService.retrieveUniversite(iduniversite);
    }

    // http://localhost:8000/university/university/remove-universite/1
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") long iduniversite) {
        universiteService.removeUniversite(iduniversite);
    }

    // http://localhost:8000/university/university/affect-foyer/3/esprit
    @PutMapping("/affect-foyer/{foyer-id}/{nom-universite}")
    public University affecterFoyerAUniversite(@PathVariable("foyer-id") long idfoyer, @PathVariable("nom-universite") String nomuniversite) {
        return universiteService.affecterFoyerAUniversite(idfoyer, nomuniversite);
    }

    // http://localhost:8000/university/university/desaffect-foyer/3/1
    @PutMapping("/desaffect-foyer/{foyer-id}/{id-universite}")
    public University desaffecterFoyerAUniversite(@PathVariable("foyer-id") long idfoyer, @PathVariable("id-universite") long iduniversite) {
        return universiteService.desaffecterFoyerAUniversite(idfoyer,iduniversite);
    }
}
