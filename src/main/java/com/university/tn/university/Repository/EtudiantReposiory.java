package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantReposiory extends JpaRepository<Etudiant, Long> {


}
