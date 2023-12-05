package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer(Foyer f);
    Foyer updateFoyer(Foyer f);
    Foyer retrieveFoyer(long idfoyer);

    void archiverFoyer(long idfoyer);

    Foyer addFoyerWithBloc(Foyer f);

}
