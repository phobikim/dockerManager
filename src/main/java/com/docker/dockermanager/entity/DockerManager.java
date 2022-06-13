package com.docker.dockermanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DockerManager {
    private String dockerId;
    private String dockerName;
    private String dockerType;
    private String dockerIp;
    private String dockerPort;
    private String dockerState;
    private String stateMsg;
    private String rgstDate;

}
