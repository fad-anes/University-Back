package com.university.tn.university.Repository;


import com.university.tn.university.Model.Entity.Bloc;
import com.university.tn.university.Model.Enum.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {


}
