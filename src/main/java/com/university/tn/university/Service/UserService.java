package com.university.tn.university.Service;

import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.Model.Entity.Etudiant;
import com.university.tn.university.Model.Entity.University;
import com.university.tn.university.Model.Enum.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.university.tn.university.Repository.UserRepository;
import java.io.UnsupportedEncodingException;
import com.university.tn.university.Repository.UniversityRepository;
import com.university.tn.university.Repository.EtudiantReposiory;
import java.util.List;
import java.util.Optional;



@Service
public class UserService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UniversityRepository UniversityRepository;
    @Autowired
    private EtudiantReposiory EtudiantReposiory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void sendEmailAdmin(String emailUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("elfadanes@gmail.com");
        message.setTo(emailUser);
        message.setSubject("MyUniversity - Confirmation de votre demande");
        String emailBody = "Bonjour Madame/Monsieur,\n\n"
                + "Nous avons bien reçu votre demande pour rejoindre notre équipe en tant qu'administrateur. "
                + "Veuillez patienter jusqu'à ce que le super administrateur vous accorde l'accès.\n\n"
                + "Cordialement,\nMyUniversity Team";
        message.setText(emailBody);
        javaMailSender.send(message);
    }

    public void sendEmailAceesAdmin(String emailUser,String nomuniversity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("elfadanes@gmail.com");
        message.setTo(emailUser);
        message.setSubject("MyUniversity - Bienvenu");
        String emailBody = "Bonjour Madame/Monsieur,\n\n"
                + "Notre équipe vous a donné l'accès à votre compte."
                + "Vous pouvez maintenant accéder à votre compte lié à l'université " + nomuniversity + ".\n\n"
                + "Cordialement,\nMyUniversity Team";
        message.setText(emailBody);
        javaMailSender.send(message);
    }
    @Transactional
    public ResponseEntity<User> Giveaccesstoadminwithuniversity(User User,Long iduniverste){
        Optional<University> existingUniversity = UniversityRepository.findById(iduniverste);
        Optional<User> existingUser = UserRepository.findById(User.getId());
        if (User.getId() == null ||iduniverste==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if (!existingUser.isPresent()||!existingUniversity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            User.setAccess(true);
            User.setUniversity(existingUniversity.get());
            UserRepository.save(User);
            sendEmailAceesAdmin(User.getEmail(),User.getUniversity().getNomuniverste());
            return ResponseEntity.ok(User);
        }
    }
    public ResponseEntity<User> register(User user) throws UnsupportedEncodingException {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = UserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        if (user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            user.setAccess(false);
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setUserrole(UserRole.valueOf("ADMIN"));
            UserRepository.save(user);
            sendEmailAdmin(user.getEmail());
            return ResponseEntity.ok(user);
        }
    }

    public ResponseEntity<User> addUser(User user,Long idetudiant) throws UnsupportedEncodingException {
        if (user == null||idetudiant==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = UserRepository.findByEmail(user.getEmail());
        Optional<Etudiant> existingEtudiant = EtudiantReposiory.findById(idetudiant);
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        if (!existingEtudiant.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (user.getEmail() == null || user.getPassword() == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setUserrole(UserRole.valueOf("ETUDIANT"));
            user.setAccess(true);
            user.setEtudiant(existingEtudiant.get());
            UserRepository.save(user);
            return ResponseEntity.ok(user);
        }
    }

    public ResponseEntity<User> changeStatus(String u){
        Optional<User> newuser=UserRepository.findByEmail(u);
        if(newuser.get().getAccess()==true)
            newuser.get().setAccess(false);
        else newuser.get().setAccess(false);
        UserRepository.save(newuser.get());
        return ResponseEntity.ok(newuser.get());
    }
    public ResponseEntity<User> updateUser( User u) {
        if(!UserRepository.existsById(u.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            String encodedPassword = passwordEncoder.encode(u.getPassword());
            u.setPassword(encodedPassword);
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
