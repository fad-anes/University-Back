package com.university.tn.university.Repository;


import com.university.tn.university.Model.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Notificationrepo extends JpaRepository<Notification, Integer> {
    List<Notification> findAllBySeen(boolean seen);
    Notification findByEmail(String email);

    long countAllBySeen(boolean seen);
}
