package com.university.tn.university.PayloadResponse;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;

    public JwtResponse(String token, String type, Integer id, String email, int statu) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.email = email;
        this.statu = statu;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String email;
    private int statu;
}
