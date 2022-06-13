package com.docker.dockermanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ping {
    String dockerName;
    Boolean dockerState;
    String stateMsg;
}
