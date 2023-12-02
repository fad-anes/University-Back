package com.university.tn.university.Model.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Entity.Etudiant;
import com.university.tn.university.Model.Entity.University;
import com.university.tn.university.Model.Enum.UserRole;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private Boolean access;
    private UserRole userrole;
    private University university;
    private Etudiant etudiant;
}
