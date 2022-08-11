package com.docker.dockermanager.repository;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.repository.Mapper.DockerManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface DockerManagerRepo extends DockerManagerMapper {
    @Override
    List<DockerManager> searchAll();

    @Override
    void updateState(DockerManager entity);

    @Override
    DockerManager findById(String id);

    @Override
    void updateById(Map<String, Object> parameter);

    List<DockerManager> searchServer(String serverIp);
}
