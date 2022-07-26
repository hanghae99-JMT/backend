package com.jmt.v1.layer.user.controller;

import com.jmt.v1.config.security.PasswordEncoder;
import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.domain.dto.request.SignupRequestDto;
import com.jmt.v1.layer.user.domain.dto.response.GetUserDataResponseDto;
import com.jmt.v1.layer.user.domain.dto.response.SigninResponseDto;
import com.jmt.v1.layer.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@Validated
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

    @GetMapping("/api/users/{id}")
    public ResponseEntity checkSignUp(
            @PathVariable("id") @Email String id
    ){
        Boolean isSignUp = userService.isUserSignUp(id);
        if(isSignUp) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/user/token")
    public ResponseEntity getUserData(
            @AuthenticationPrincipal User user
    ){
        return new ResponseEntity(new GetUserDataResponseDto(user), HttpStatus.OK);
    }
}
