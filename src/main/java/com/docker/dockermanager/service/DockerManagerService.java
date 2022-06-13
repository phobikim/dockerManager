package com.docker.dockermanager.service;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.entity.Ping;
import com.docker.dockermanager.repository.DockerManagerRepository;
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
    private DockerManagerRepository dockerManagerRepository;

    public List<DockerManager> showList() {
        return dockerManagerRepository.searchAll();
    }
    public DockerManager findById(String id) {
        return dockerManagerRepository.findById(id);
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
        dockerManagerRepository.updateById(parameter);
    }
    public void updateState(List<DockerManager> dockerManagers){
        HealthCheck healthCheck = new HealthCheck();
        List<Ping> result = healthCheck.callCheckMethod(dockerManagers);
        for(DockerManager var : dockerManagers){
            for(Ping ping : result){
                if(var.getDockerName().equals(ping.getDockerName()) ){
                    var.setDockerState(ping.getDockerState().toString());
                    var.setStateMsg(ping.getStateMsg());
                    var.setRgstDate(sdf.format(new Date()));
                    dockerManagerRepository.updateState(var);
                }
            }
        }
    }
}
