package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.university.tn.university.Repository.UserRepository;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<User> addUser(User user) throws UnsupportedEncodingException, MessagingException {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = UserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }

        if (user.getEmail() == null || user.getPassword() == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setStatu(1);
            UserRepository.save(user);
            return ResponseEntity.ok(user);
        }
    }

    public ResponseEntity<User> changeStatus(String u){
        Optional<User> newuser=UserRepository.findByEmail(u);
        if(newuser.get().getStatu()==0)
            newuser.get().setStatu(1);
        else newuser.get().setStatu(0);
        UserRepository.save(newuser.get());
        return ResponseEntity.ok(newuser.get());
    }
    public ResponseEntity<User> updateUser( User u) {
        if(!UserRepository.existsById(u.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            String encodedPassword = passwordEncoder.encode(u.getPassword());
            u.setPassword(encodedPassword);
            u.setStatu(1);
            UserRepository.save(u);
            return ResponseEntity.ok(u);
        }

    }
    public List<User> retrieveAllUsers() {
        return UserRepository.findAll();
    }
    public Optional<User> retrieveUser(String email){
        return UserRepository.findByEmail(email);
    }
    public void removeUser(Integer idUser) {
        UserRepository.deleteById(Integer.valueOf(idUser));
    }


}
