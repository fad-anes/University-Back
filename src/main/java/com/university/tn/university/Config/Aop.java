package com.university.tn.university.Config;

import com.university.tn.university.Model.Entity.Notification;
import com.university.tn.university.Repository.Notificationrepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class Aop {
    @Autowired
    private Notificationrepo Notificationrepo;

    @After("execution(* com.university.tn.university.Service.ServiceNotification.gettallunseen(..))")
    public void markAllNotificationsAsSeen() {
        List<Notification> unseenNotifications = Notificationrepo.findAllBySeen(false);
        for (Notification notification : unseenNotifications) {
            notification.setSeen(true);
        }
        Notificationrepo.saveAll(unseenNotifications);
    }
}
