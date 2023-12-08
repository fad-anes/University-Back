package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Etudiant;
import jakarta.persistence.TemporalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EtudiantReposiory extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant>  findByNomAndPrenom(String nom , String prenom);

    @Query("SELECT e FROM Etudiant e " +
            "WHERE (:nom IS NULL OR LOWER(e.nom) LIKE LOWER(CONCAT('%', :nom, '%'))) " +
            "AND (:prenom IS NULL OR LOWER(e.prenom) LIKE LOWER(CONCAT('%', :prenom, '%'))) " +
            "AND (:cin IS NULL OR e.cin = :cin) " +
            "AND (:ecole IS NULL OR LOWER(e.ecole) LIKE LOWER(CONCAT('%', :ecole, '%'))) " +
            "AND (:datenaissance IS NULL OR e.datenaissance = :datenaissance)")
    Page<Etudiant> searchAndFilter(
            String nom, String prenom, Long cin, String ecole,
            @Temporal(TemporalType.DATE) Date datenaissance, Pageable pageable
    );
}
