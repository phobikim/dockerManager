package com.docker.dockermanager.util;

public class DockerException extends Exception {
    int code;
    public DockerException(String msg) {
        super(msg);
    }
    public DockerException(String msg, Throwable e) {
        super(msg,e);
    }
    public DockerException(int code, String msg) {
        super(msg);
        this.code = code;
    }
    public DockerException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
}
