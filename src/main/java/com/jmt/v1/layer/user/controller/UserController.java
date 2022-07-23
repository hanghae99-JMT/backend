package com.jmt.v1.layer.user.controller;

import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.domain.dto.request.SignupRequestDto;
import com.jmt.v1.layer.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/api/signup")
    public ResponseEntity signup(
            @RequestBody @Valid SignupRequestDto signupRequestDto
            ){
        userService.save(signupRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
