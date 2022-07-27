package com.jmt.v1.layer.user.domain.dto.response;

import com.jmt.v1.layer.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponseDto {
    private String id;
    private String username;
    private String token;

    public SigninResponseDto(User user, String token){
        this.id = user.getEmail();
        this.username = user.getName();
        this.token = "Bearer " + token;
    }
}
