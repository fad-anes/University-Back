package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Service.IBlocService;
import com.university.tn.university.Service.IChambreService;
import com.university.tn.university.Service.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    IBlocService blocService;

    IChambreService chambreService;

    IReservationService reservationService;


    // http://localhost:8000/university/bloc/retrieve-all-blocs
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> retrieveAllBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }

    // http://localhost:8000/university/bloc/add-bloc
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.addBloc(b);
        return bloc;
    }

    // http://localhost:8000/university/bloc/update-bloc
    @PutMapping("/update-bloc/{idbloc}")
    public Bloc updateBloc(@RequestBody Bloc b, @PathVariable long idbloc) {
        Bloc bloc = blocService.updateBloc(b,idbloc);
        return bloc;
    }

    // http://localhost:8000/university/bloc/retrieve-bloc/bloc-id
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") long idbloc) {
        return blocService.retrieveBloc(idbloc);
    }

    // http://localhost:8000/university/bloc/remove-bloc/bloc-id
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") long idbloc) {
        blocService.removeBloc(idbloc);
    }

    @PutMapping("/affecterChambres/{numeroChambre}/{nombloc}")
    public Bloc affecterChambresABloc(@PathVariable("numeroChambre") List<Long> numeroChambre, @PathVariable("nombloc") String nombloc) {
        return blocService.affecterChambresABloc(numeroChambre, nombloc);
    }

    @GetMapping("/compare-blocs")
    public Map<String, String> compareBlocs() {
        return blocService.comparerBlocsParTypeChambres();
    }


}
