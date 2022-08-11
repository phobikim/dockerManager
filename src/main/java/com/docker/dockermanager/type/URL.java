package com.docker.dockermanager.type;

public enum URL {
    DOCKER_MAIN("/dockerMain" , "도커 리스트 페이지"),
    LOGIN("/auth" , "도커 매니저 로그인 페이지");

    private String code;
    private String desc;

    URL(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
