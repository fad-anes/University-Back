package com.university.tn.university.PayloadResponse;

import com.university.tn.university.Model.Enum.UserRole;

public class JwtResponse {
    private String token;
    private Integer id;
    private String email;

    public JwtResponse(String token, Integer id, String email, UserRole role, int statu) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
        this.statu = statu;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    private UserRole role;
    private int statu;
}
