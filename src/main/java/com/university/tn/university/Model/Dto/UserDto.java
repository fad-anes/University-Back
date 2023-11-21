package com.university.tn.university.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.tn.university.Model.Enum.UserRole;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private String PasswordToken;
    private int statu;
    private UserRole userrole;
}
