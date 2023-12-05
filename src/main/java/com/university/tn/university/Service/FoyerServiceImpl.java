package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Repository.BlocRepository;
import com.university.tn.university.Repository.FoyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {
    FoyerRepository foyerRepository;
    BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idfoyer) {
        return foyerRepository.findById(idfoyer).get();
    }

    public void archiverFoyer(long idfoyer) {
        Optional<Foyer> optionalFoyer = foyerRepository.findById(idfoyer);

        optionalFoyer.ifPresent(foyer -> {
            foyer.setArchived(true);
            foyerRepository.save(foyer);
        });
    }
    @Override
    public Foyer addFoyerWithBloc (Foyer f){

        Foyer foyer = foyerRepository.save(f);

        foyer.getBlocs().forEach(bloc ->
        {

            bloc.setFoyer(foyer);
            blocRepository.save(bloc);
        });
        return foyer;
    }


}
