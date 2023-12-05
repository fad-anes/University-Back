package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

}
