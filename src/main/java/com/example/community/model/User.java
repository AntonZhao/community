package com.example.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountID;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
