package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Bloc;

import java.util.List;
import java.util.Map;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();
    Bloc addBloc(Bloc b);
    Bloc updateBloc(Bloc b, long idbloc);
    Bloc retrieveBloc(long idbloc);
    void removeBloc(long idbloc);

    Bloc affecterChambresABloc(List<Long> numeroChambre, String nombloc);

    Map<String, String> comparerBlocsParTypeChambres();
}
