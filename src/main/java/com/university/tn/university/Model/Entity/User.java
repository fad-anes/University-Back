package com.university.tn.university.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.university.tn.university.Model.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String PasswordToken;
    @Enumerated(EnumType.STRING)
    private UserRole userrole;
    private int statu;
    @ManyToOne
    //@JsonBackReference
    private University university;
    @OneToOne
    private Etudiant etudiant;
}
