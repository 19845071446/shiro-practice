package com.example.shiropractice.WithSpringBoot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    String id;
    String username;
    String password;
    String salt;
    List<String> roles;
}
