package com.parking.engine.service.impl;

import com.parking.engine.repository.RoleRepository;
import com.parking.engine.security.CustomUserDetails;
import com.parking.engine.entity.User;
import com.parking.engine.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        List<String> roles = roleRepository.getRole(user.getUserId());
        return new CustomUserDetails(user, roles);
    }
}
