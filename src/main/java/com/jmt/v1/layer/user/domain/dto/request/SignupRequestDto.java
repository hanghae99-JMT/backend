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
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 패턴 맞춰주쇼!")
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
