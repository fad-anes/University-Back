package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {

}
