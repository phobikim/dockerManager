package com.docker.dockermanager.entity;

import lombok.Data;

@Data
public class DockerManager implements Entity {
    private String id;
    private String dockerId;
    private String dockerName;
    private String dockerType;
    private String dockerIp;
    private String dockerPort;
    private String dockerState;
    private String stateMsg;
    private String rgstDate;
    private String updateDate;
    private String version;
    private String accountId;
    private String accountpw;
    private Ping ping;

}
