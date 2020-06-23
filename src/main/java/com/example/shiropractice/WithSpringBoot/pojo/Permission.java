package com.example.shiropractice.WithSpringBoot.pojo;

import lombok.Data;

@Data
public class Permission {
    private String id;
    private String url;//user:*:*
    private String name;// /user
}
