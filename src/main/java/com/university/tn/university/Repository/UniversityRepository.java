package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    University findByNomuniverste(String nom);
    @Query("SELECT DISTINCT nomuniverste FROM University")
    List<String> findDistinctNomuniversteBy();
}
