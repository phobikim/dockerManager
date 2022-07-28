package com.docker.dockermanager.util;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.entity.Ping;
import com.docker.dockermanager.message.DockerCode;
import com.docker.dockermanager.service.DockerManagerService;
import com.docker.dockermanager.type.STATE;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.Oneway;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HealthCheck {
    private List<Ping> result = new ArrayList<>();
    public List<Ping> callCheckMethod(List<DockerManager> dockerManagers) {
        try{
            for(DockerManager var : dockerManagers){
                result.add(checkAndUpdateDockerState(var));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public Ping checkAndUpdateDockerState(@NotNull DockerManager dockerManagers) throws IOException, DockerException {
        Socket socket = new Socket();
        String pingState = STATE.STOP.getCode();
        int timeout = 2000;
        if (Integer.parseInt(dockerManagers.getDockerPort()) >= 65535) {
            pingState = STATE.ERROR.getCode();
            new Ping(dockerManagers.getDockerName(), socket.isConnected(), pingState);
        } else {
            try {
                socket.connect(new InetSocketAddress(dockerManagers.getDockerIp(), Integer.parseInt(dockerManagers.getDockerPort())), timeout);
                pingState = STATE.RUNNING.getCode();
            } catch (SocketTimeoutException e) {
                pingState = STATE.STOP.getCode();
//                return new Ping(dockerManagers.getDockerName(), socket.isConnected(), STATE.STOP.getCode());
            } finally {
                socket.close();
            }
        }
        return new Ping(dockerManagers.getDockerName(), socket.isConnected(), pingState);
    }
}
