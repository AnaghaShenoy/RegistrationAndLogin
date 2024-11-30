package com.study.registrationsystem.security;

import com.study.registrationsystem.entity.User;
import com.study.registrationsystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);

//        if(user != null) {
//            return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                    user.getPassword(),
//                    user.getRoles().stream()
//                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                            .collect(Collectors.toList());
//            )}
//            else {
//                throw new UsernameNotFoundException("Invalid username or password");
//            }
        if(user!=null){//if we user is not null, then we'll convert this user entity to spring security
            // Provided user object. So here let's return new user object and then pass user object
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    ,user.getPassword()
                    ,user.getRoles()//we need to convert this list of roles into a list of simple granted authority objects.
                    .stream()
                    .map((role)->new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList()));
        }
        else{
            throw new UsernameNotFoundException("User not found with email: "+usernameOrEmail);

        }
    }
}
