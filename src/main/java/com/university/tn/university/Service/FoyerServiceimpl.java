package com.university.tn.university.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Repository.FoyerRepository;
import java.util.List;
import java.util.Optional;


@Service
public class FoyerServiceimpl implements InterfaceFoyerService{
    @Autowired
    private FoyerRepository Foyerrepo;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return Foyerrepo.findAll();
    }

    @Override
    public ResponseEntity<Foyer> addFoyer(Foyer Foyer)  {
        if(Foyer.getIdfoyer()!=null) {
        Optional<Foyer> existingFoyer = Foyerrepo.findById(Foyer.getIdfoyer());
        if (existingFoyer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }}
         if (Foyer.getNomfoyer() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Foyer.setArchived(false);
            Foyerrepo.save(Foyer);
            return ResponseEntity.ok(Foyer);
        }
    }

    @Override
    public ResponseEntity<Foyer> updateFoyer(Foyer Foyer)  {
        Optional<Foyer> existingFoyer = Foyerrepo.findById(Foyer.getIdfoyer());
        if (!existingFoyer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       else {
            Foyerrepo.save(Foyer);
            return ResponseEntity.ok(Foyer);
        }
    }
    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return Foyerrepo.getReferenceById(idFoyer);
    }

    @Override
    public void archiverFoyer(long idFoyer) {
        Foyer foyer = Foyerrepo.findById(idFoyer).orElse(null);
        if (foyer != null) {
            if(foyer.getArchived()==true)
                foyer.setArchived(false);
            else foyer.setArchived(true);

            Foyerrepo.save(foyer);
        }

    }

    @Transactional
    public ResponseEntity<Foyer> addFoyerWithBloc(Foyer Foyer)  {
        Optional<Foyer> existingFoyer = Foyerrepo.findById(Foyer.getIdfoyer());
        if (!existingFoyer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (Foyer.getBlocs()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Foyerrepo.save(Foyer);
            return ResponseEntity.ok(Foyer);
        }
    }
}
