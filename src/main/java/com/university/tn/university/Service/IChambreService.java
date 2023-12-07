package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Enum.TypeChambre;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IChambreService {

    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c);
    Chambre updateChambre(Chambre c, long idchambre);
    Chambre retrieveChambre(long idchambre);
    void removeChambre(long idchambre);

    List<Chambre> getChambresParNomBloc(String nombloc);

    long nbChambreParTypeEtBloc(TypeChambre typec, long idbloc);

    List<Chambre> filterChambresByBloc(String nombloc);


}
