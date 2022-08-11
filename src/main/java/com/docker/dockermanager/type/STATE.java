package com.docker.dockermanager.type;

import lombok.AllArgsConstructor;
import lombok.Data;


public enum STATE {
    //Health-check 관련
    RUNNING("RUNNING" , "실행중"),
    STOP("STOP","정지"),
    ERROR("ERROR","에러"),

    //Server Ip 관련
    STAG_SERVER("192.168.0.182","스테이징 서버"),
    DEV_SERVER("192.168.0.183" , "개발 서버"),
    DB_SERVER("192.168.0.105" , "디비 서버");
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
