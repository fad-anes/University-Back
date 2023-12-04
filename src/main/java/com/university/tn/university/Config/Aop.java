package com.university.tn.university.Config;

import com.university.tn.university.Model.Entity.Notification;
import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.Service.UserService;
import com.university.tn.university.Repository.Notificationrepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Aspect
@Component
@Slf4j
public class Aop {
    @Autowired
    private Notificationrepo Notificationrepo;
    @Autowired
    private UserService usersService;

    @After("execution(* com.university.tn.university.Service.ServiceNotification.gettallunseen(..))")
    public void markAllNotificationsAsSeen() {
        List<Notification> unseenNotifications = Notificationrepo.findAllBySeen(false);
        for (Notification notification : unseenNotifications) {
            notification.setSeen(true);
        }
        Notificationrepo.saveAll(unseenNotifications);
    }

    @AfterReturning(
            pointcut = "execution(* com.university.tn.university.Service.UserService.register(..))",
            returning = "responseEntity"
    )
    public void sendEmailAfterRegistration(JoinPoint joinPoint, ResponseEntity<?> responseEntity) throws UnsupportedEncodingException {
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            Object body = responseEntity.getBody();
            if (body instanceof User) {
                User user = (User) body;
                usersService.sendEmailAdmin(user.getEmail());
            }
        }
    }
}
