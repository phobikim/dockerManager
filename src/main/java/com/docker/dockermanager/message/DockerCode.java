package com.docker.dockermanager.message;

public enum DockerCode {
    DOCKER_SUCCESS(200,"성공"),
    DOCKER_HEALTH_CHECK_ERROR(10,"HEALTH CHECK 함수에서 에러가 발생하였음"),
    DOCKER_PORT_RANGE_ERROR(11,"PORT 범위 초과");
    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    DockerCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
