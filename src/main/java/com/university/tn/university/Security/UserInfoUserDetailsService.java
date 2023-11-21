package com.university.tn.university.Security;

import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email not found"));
        return build(user);
    }

    public static UserInfoUserDetails build(User user){
        return new UserInfoUserDetails(user.getId(), user.getEmail(), user.getPassword(),user.getUserrole(), user.getStatu());
    }
}
