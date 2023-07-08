package com.learning.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name="contacts")
@Getter
@Setter
public class Contact{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String contact;
    @ManyToOne
    @JsonIgnore
    private User user;
}
