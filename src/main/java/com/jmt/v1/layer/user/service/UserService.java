package com.jmt.v1.layer.user.service;

import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.domain.dto.request.SignupRequestDto;
import com.jmt.v1.layer.user.infra.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException(email));
    }

    public Boolean isUserSignUp(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }


    public User save(SignupRequestDto signupRequestDto){
        User user = signupRequestDto.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
