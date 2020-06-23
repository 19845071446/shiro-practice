package com.example.shiropractice.WithSpringBoot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String id;
    private String name;
    private List<Permission> perms;

}
