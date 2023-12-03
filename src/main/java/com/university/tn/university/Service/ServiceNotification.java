package com.university.tn.university.Service;
import com.university.tn.university.Model.Entity.Notification;
import com.university.tn.university.Repository.Notificationrepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceNotification {

    @Autowired
    private Notificationrepo Notificationrepo;
    @Transactional
    public List<Notification> gettallunseen(){
        return Notificationrepo.findAllBySeen(false);
    }
    public long nombredenotification(){
        return Notificationrepo.countAllBySeen(false);
    }

    @Scheduled(fixedRate = 60000)
    public void sowunseen(){
        List<Notification> ns =Notificationrepo.findAllBySeen(false);
        for (Notification notification : ns) {
          log.info("id notification"+notification.getId()+"message :"+notification.getMessage());
        }
    }
}
