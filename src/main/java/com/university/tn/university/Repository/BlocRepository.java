package com.university.tn.university.Repository;


import com.university.tn.university.Model.Entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    public Bloc findByNombloc(String nombloc);

}
