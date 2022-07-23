package com.jmt.v1.layer.user.domain.dto.response;

import com.jmt.v1.layer.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDataResponseDto {
    private String id;
    private String username;

    public GetUserDataResponseDto(User user){
        this.id = user.getEmail();
        this.username = user.getName();
    }
}
