package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {
    public University findByNomuniverste(String nomuniverste);
}
