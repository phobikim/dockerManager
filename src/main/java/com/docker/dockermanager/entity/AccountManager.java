package com.docker.dockermanager.entity;

import lombok.Data;

@Data
public class AccountManager implements Entity {
    private String id;
    private String dockerId;
    private String adminId;
    private String adminPw;
    private String userId;
    private String userPw;
    private String userId2;
    private String userPw2;
    private String note;
}
