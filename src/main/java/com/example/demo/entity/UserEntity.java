package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;

    private String username;
    private String password;
    private Date createTime;
    private Date lastLoginTime;
}
