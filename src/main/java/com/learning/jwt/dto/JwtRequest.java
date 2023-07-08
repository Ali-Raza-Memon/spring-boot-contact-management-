package com.learning.jwt.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class JwtRequest {
    String username;
    String password;
}
