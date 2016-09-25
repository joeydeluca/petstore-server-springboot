package com.petservice.security;

import com.petservice.domain.user.User;
import com.petservice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joe Deluca on 9/24/2016.
 */

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("Cannot find user");

        List<GrantedAuthority> authorityCollections = new ArrayList();
        authorityCollections.add(() -> user.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true, true, true, authorityCollections);
    }
}
