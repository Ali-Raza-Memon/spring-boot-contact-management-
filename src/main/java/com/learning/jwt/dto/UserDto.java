package com.learning.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Integer id;
    private String fullName;
    private String email;
    private String address;
    private String password;
    private String cnic;

}
