package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Enum.TypeChambre;
import com.university.tn.university.Service.IBlocService;
import com.university.tn.university.Service.IChambreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
@CrossOrigin(origins = "http://localhost:4200")
public class ChambreController {
    IChambreService chambreService;
    IBlocService blocService;

    // http://localhost:8000/university/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> retrieveAllChambres(){
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }

    // http://localhost:8000/university/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre (@RequestBody Chambre c){
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8000/university/chambre/update-chambre
    @PutMapping("/update-chambre/{idchambre}")
    public Chambre updateChambre (@RequestBody Chambre c, @PathVariable long idchambre){
        Chambre chambre= chambreService.updateChambre(c, idchambre);
        return chambre;
    }

    // http://localhost:8000/university/chambre/retrieve-chambre/chambre-id
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") long idchambre){
        return chambreService.retrieveChambre(idchambre);
    }

    // http://localhost:8000/university/chambre/remove-chambre/chambre-id
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") long idchambre){
        chambreService.removeChambre(idchambre);
    }

    // http://localhost:8000/university/chambre/chambres-par-nom-bloc?nomBloc=NOM_DU_BLOC
    @GetMapping("/chambres-par-nom-bloc")
    public List<Chambre> getChambresParNomBloc(@RequestParam String nombloc) {
        return chambreService.getChambresParNomBloc(nombloc);
    }

    // http://localhost:8000/university/chambre/nb-chambre-par-type-et-bloc?type=DOUBLE&idBloc=1
    @GetMapping("/nb-chambre-par-type-et-bloc")
    public long nbChambreParTypeEtBloc(@RequestParam TypeChambre typec, @RequestParam long idbloc) {
        return chambreService.nbChambreParTypeEtBloc(typec, idbloc);
    }

    @GetMapping("/filter-chambres-bloc")
    public List<Chambre> filterChambresByBloc(@RequestParam String nombloc) {
    return chambreService.filterChambresByBloc(nombloc);
     }


}
