package com.docker.dockermanager.service;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.entity.Ping;
import com.docker.dockermanager.repository.DockerManagerRepo;
import com.docker.dockermanager.util.HealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DockerManagerService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private DockerManagerRepo dockerManagerRepo;

    public List<DockerManager> showList() {
        return dockerManagerRepo.searchAll();
    }
    public DockerManager findById(String id) {
        return dockerManagerRepo.findById(id);
    }
    public void updateById(String dockerId , DockerManager dockerManager){
        Map<String , Object> parameter = new HashMap<>();

        DockerManager findDocker = findById(dockerId);
        findDocker.setDockerName(dockerManager.getDockerName());
        findDocker.setDockerIp(dockerManager.getDockerIp());
        findDocker.setDockerPort(dockerManager.getDockerPort());


        parameter.put("dockerId", dockerId);
        parameter.put("dockerName", findDocker.getDockerName());
        parameter.put("dockerIp", findDocker.getDockerIp());
        parameter.put("dockerPort", findDocker.getDockerPort());
        dockerManagerRepo.updateById(parameter);
    }
    public void updateState(List<DockerManager> dockerManagers){
        HealthCheck healthCheck = new HealthCheck();
        List<Ping> result = healthCheck.callCheckMethod(dockerManagers);
        for(DockerManager var : dockerManagers){
            for(Ping ping : result){
                if(var.getDockerName().equals(ping.getDockerName()) ){
                    var.setDockerState(ping.getDockerState().toString());
                    var.setStateMsg(ping.getStateMsg());
                    var.setUpdateDate(sdf.format(new Date()));
                    dockerManagerRepo.updateState(var);
                }
            }
        }
    }
}
