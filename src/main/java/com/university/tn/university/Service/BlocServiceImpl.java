package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Enum.TypeChambre;
import com.university.tn.university.Repository.BlocRepository;
import com.university.tn.university.Repository.ChambreRepository;
import com.university.tn.university.Repository.FoyerRepository;
import com.university.tn.university.Repository.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {

    BlocRepository blocRepository;
    FoyerRepository foyerRepository;
    ChambreRepository chambreRepository;
    ReservationRepository reservationrepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b ,long idbloc) {
        b.setIdbloc(idbloc);
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(long idbloc) {
        return blocRepository.findById(idbloc).get();
    }

    @Override
    public void removeBloc(long idbloc) {
        blocRepository.deleteById(idbloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numeroChambre, String nombloc) {
        //recuperer le bloc
        Bloc bloc = blocRepository.findByNombloc(nombloc);
        for ( int i = 0 ; i<numeroChambre.size(); i++){
            //recuperer chambre
            Chambre chambre = chambreRepository.findByNumeroChambre(numeroChambre.get(i));
            //affecter le bloc a chambre
            chambre.setBloc(bloc);
            //enregistrer les modifications de la chambre
            chambreRepository.save(chambre);
        }
        return bloc;
    }

    private int countChambresOfType(Bloc bloc, TypeChambre type) {
        return (int) bloc.getChambres().stream()
                .filter(chambre -> chambre.getTypec() == type)
                .count();
    }

    public Map<String, String> comparerBlocsParTypeChambres() {
        List<Bloc> blocs = blocRepository.findAll();

        Map<String, String> blocsAvecPlusDeTypes = new HashMap<>();

        // Compare blocs for each type and add the names to the map
        compareBlocsForType(blocs, TypeChambre.DOUBLE, blocsAvecPlusDeTypes, "doubles");
        compareBlocsForType(blocs, TypeChambre.SIMPLE, blocsAvecPlusDeTypes, "simples");
        compareBlocsForType(blocs, TypeChambre.TRIPLE, blocsAvecPlusDeTypes, "triples");

        return blocsAvecPlusDeTypes;
    }

    private void compareBlocsForType(List<Bloc> blocs, TypeChambre typeChambre, Map<String, String> blocsAvecPlusDeTypes, String typeLabel) {
        Bloc blocAvecPlusDeType = blocs.stream()
                .max(Comparator.comparingInt(bloc -> countChambresOfType(bloc, typeChambre)))
                .orElse(null);

        if (blocAvecPlusDeType != null) {
            blocsAvecPlusDeTypes.put(typeLabel, blocAvecPlusDeType.getNombloc());
        }
    }



}
