package com.university.tn.university.Repository;

import com.university.tn.university.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
