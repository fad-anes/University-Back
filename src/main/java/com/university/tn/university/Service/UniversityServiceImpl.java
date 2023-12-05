package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.Foyer;
import com.university.tn.university.Model.Entity.University;
import com.university.tn.university.Repository.FoyerRepository;
import com.university.tn.university.Repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversityServiceImpl implements IUniversityService {
    UniversityRepository universiteRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<University> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public University addUniversite(University u) {
        return universiteRepository.save(u);
    }

    @Override
    public University updateUniversite(University u) {
        return universiteRepository.save(u);
    }

    @Override
    public University retrieveUniversite(long iduniversite) {
        return universiteRepository.findById(iduniversite).get();
    }

    @Override
    public void removeUniversite(long iduniversite) {
        universiteRepository.deleteById(iduniversite);
    }

    @Override
    public University affecterFoyerAUniversite(long idfoyer, String nomuniverste) {
        Foyer f = foyerRepository.findById(idfoyer).get();
        University university = universiteRepository.findByNomuniverste(nomuniverste);
        university.setFoyer(f);
        universiteRepository.save(university);
        return university;
    }

    @Override
    public University desaffecterFoyerAUniversite (long idfoyer, long iduniversite){
        University university = universiteRepository.findById(iduniversite).get();
        university.setFoyer(null);
        universiteRepository.save(university);
        return university;
    }

}
