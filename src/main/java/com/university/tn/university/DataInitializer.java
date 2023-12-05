package com.university.tn.university;


import com.university.tn.university.Model.Enum.UserRole;
import com.university.tn.university.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.university.tn.university.Model.Entity.User;
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private  UserRepository UserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (UserRepository.count() == 0) {
            User user = new User();
            String encodedPassword = passwordEncoder.encode("superadmin");
            user.setPassword(encodedPassword);
            user.setEmail("elfadanes@gmail.com");
            user.setUserrole(UserRole.valueOf("SUPERADMIN"));
            user.setAccess(true);
            UserRepository.save(user);
        }
    }
}
