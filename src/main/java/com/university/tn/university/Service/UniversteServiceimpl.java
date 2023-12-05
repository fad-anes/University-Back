package com.university.tn.university.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Model.Entity.University;
import com.university.tn.university.Repository.UniversityRepository;
import com.university.tn.university.Repository.FoyerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UniversteServiceimpl implements InterfcaeUniversteService{
    @Autowired
    private UniversityRepository RepoUniverste;
    @Autowired
    private FoyerRepository Foyerrepository;
    @Override
    public List<University> retrieveAllUniversities() {
        return RepoUniverste.findAll();
    }

    @Override
    public ResponseEntity<University> addUniversity(University u)  {
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        University existingUniversity = RepoUniverste.findByNomuniverste(u.getNomuniverste());
        if (existingUniversity!=null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        if (u.getNomuniverste() == null || u.getAdresse() == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            RepoUniverste.save(u);
            return ResponseEntity.ok(u);
        }
    }
    @Override
    public ResponseEntity<University> updateUniversity( University u) {
        if(!RepoUniverste.existsById(u.getIduniverste())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            RepoUniverste.save(u);
            return ResponseEntity.ok(u);
        }

    }
    @Override
    public University retrieveUniversity(long idUniversity) {
        return RepoUniverste.getReferenceById(idUniversity);
    }

    @Override
    public void removeUniversity(long idUniversity) {
        RepoUniverste.deleteById(idUniversity);
    }

    @Transactional
    public ResponseEntity<University> affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        University universte = RepoUniverste.findByNomuniverste(nomUniversite);
        Optional<Foyer> foyerOptional = Foyerrepository.findById(idFoyer);
        if(!foyerOptional.isPresent()||universte==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
                Foyer foyer = foyerOptional.get();
                universte.setFoyer(foyer);
                University updatedUniverste =RepoUniverste.save(universte) ;
                return ResponseEntity.ok(updatedUniverste);
        }
    }

    @Transactional
    public ResponseEntity<University> desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Optional<University> UniversteOptional = RepoUniverste.findById(idUniversite);
        Optional<Foyer> foyerOptional = Foyerrepository.findById(idFoyer);
        if (!foyerOptional.isPresent() || !UniversteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (UniversteOptional.get().getFoyer() != foyerOptional.get()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            University Universte = UniversteOptional.get();
            Universte.setFoyer(null);
            University updatedUniverste = RepoUniverste.save(Universte);
            return ResponseEntity.ok(updatedUniverste);
        }
    }

}
