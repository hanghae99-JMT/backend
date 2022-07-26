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
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException(email));
    }

    public void isUserSignUp(String email) throws UsernameNotFoundException {
        if(userRepository.existsByEmail(email))
            throw new RuntimeException("중복된 이메일 입니다.");
    }

    public User save(SignupRequestDto signupRequestDto){
        User user = signupRequestDto.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
