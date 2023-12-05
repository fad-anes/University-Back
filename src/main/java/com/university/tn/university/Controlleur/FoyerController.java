package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Service.IFoyerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {
    IFoyerService foyerService;
    // http://localhost:8000/university/foyer/retrieve-all-foyers
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> retrieveAllFoyers(){
        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();
        return listFoyers;
    }

    // http://localhost:8000/university/foyer/add-foyer
    @PostMapping("/add-foyer")
    public Foyer addFoyer (@RequestBody Foyer f){
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    // http://localhost:8000/university/foyer/update-foyer
    @PutMapping("/update-foyer")
    public Foyer updateFoyer (@RequestBody Foyer f){
        Foyer foyer= foyerService.updateFoyer(f);
        return foyer;
    }

    // http://localhost:8000/university/foyer/retrieve-foyer/8
    @GetMapping("/retrieve-foyer/{id-foyer}")
    public Foyer retrieveFoyer(@PathVariable("id-foyer") long idfoyer){
        return foyerService.retrieveFoyer(idfoyer);
    }

    // http://localhost:8000/university/foyer/archiver-foyer/1
    @PostMapping("/archiver-foyer/{id-foyer}")
    public void archiverFoyer(@PathVariable("id-foyer") long idfoyer) {
        foyerService.archiverFoyer(idfoyer);

    }

    // http://Localhost:8089/springProject/bloc/addFoyerWithBloc
    @PostMapping("/addFoyerWithBloc")
    @ResponseBody
    public Foyer addFoyerWithBloc(@RequestBody Foyer f) {
        Foyer foyer= foyerService.addFoyerWithBloc(f);
        return foyer;
    }


}
