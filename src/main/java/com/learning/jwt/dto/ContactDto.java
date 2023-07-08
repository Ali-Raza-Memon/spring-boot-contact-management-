package com.learning.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactDto {
    private Integer id;
    private Integer userId;
    private String contact;

}
