package com.jmt.v1.layer.user.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequestDto {
    @Email
    private String id;
    private String pw;
}
