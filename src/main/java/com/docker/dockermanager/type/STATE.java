package com.docker.dockermanager.type;

import lombok.AllArgsConstructor;
import lombok.Data;


public enum STATE {
    RUNNING("RUNNING" , "실행중"),
    STOP("STOP","정지"),
    ERROR("ERROR","에러");

    private String code;
    private String desc;

    STATE(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
