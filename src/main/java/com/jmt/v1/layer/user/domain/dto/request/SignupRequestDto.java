package com.jmt.v1.layer.user.domain.dto.request;

import com.jmt.v1.layer.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    @Email
    private String id;
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[@$!%*#?&])[A-Za-z@$!%*#?&]{8,16}$", message = "영문자와 특수문자(!@#$%^&*)포함 8-16자로 입력해주세요.")
    private String pw;

    public User toUser(){
        return User.builder()
                .email(this.id)
                .password(this.pw)
                .name(this.username)
                .enabled(true)
                .authorityList(new SimpleGrantedAuthority("ROLE_USER"))
                .build();
    }
}
