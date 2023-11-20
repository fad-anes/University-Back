package com.university.tn.university.Repository;


import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Enum.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {

    List<Bloc> findByFoyerUniversityNomuniverste(String nomUniverste);
    Bloc findByNombloc(String nom);
    @Query("SELECT b FROM Bloc b WHERE b.foyer.university.nomuniverste =?1")
    List<Bloc> RetrieveBlocbyUniversity(String nom);

    long  countChambresByIdblocAndChambresTypec(long idBloc, TypeChambre type);
}
