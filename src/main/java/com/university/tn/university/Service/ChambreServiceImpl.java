package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Entity.Chambre;
import com.university.tn.university.Model.Enum.TypeChambre;
import com.university.tn.university.Repository.BlocRepository;
import com.university.tn.university.Repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    ChambreRepository chambreRepository;
    BlocRepository blocRepository;


    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c, long idchambre) {
        c.setIdchambre(idchambre);
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idchambre) {
        return chambreRepository.findById(idchambre).get();
    }

    @Override
    public void removeChambre(long idchambre) {
        chambreRepository.deleteById(idchambre);
    }
    @Override
    public List<Chambre> getChambresParNomBloc(String nombloc) {
        Bloc bloc = blocRepository.findByNombloc(nombloc);

        if (bloc != null) {
            Set<Chambre> chambres = bloc.getChambres();
            return new ArrayList<>(chambres);
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre typec, long idbloc) {
        return chambreRepository.countByTypecAndBlocIdbloc(typec, idbloc);
    }

    public List<Chambre> filterChambresByBloc(String nombloc) {
        return chambreRepository.findByBlocNombloc(nombloc);
    }


}
