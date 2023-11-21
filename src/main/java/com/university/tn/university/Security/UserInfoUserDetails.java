package com.university.tn.university.Security;

import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.Model.Enum.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String password;
    private UserRole role;
    private int statu;

    public UserInfoUserDetails(Integer id,String email,String password,UserRole role,int statu){
        this.id=id;
        this.email=email;
        this.password=password;
        this.role=role;
        this.statu=statu;
    }
    public UserRole getRole() {
        return this.role;
    }
    public Integer getId() {
        return id;
    }
    public int getStatu() {
        return statu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInfoUserDetails user = (UserInfoUserDetails) o;
        return Objects.equals(id, user.id);
    }
}
