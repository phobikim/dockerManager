package com.docker.dockermanager.repository;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.repository.Mapper.DockerManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DockerManagerRepoDetail implements DockerManagerRepo {

    @Autowired
    DockerManagerMapper managerMapper;

    @Override
    public List<DockerManager> searchAll() {
        return managerMapper.searchAll();
    }

    @Override
    public void updateState(DockerManager entity) {
        managerMapper.updateState(entity);
    }

    @Override
    public DockerManager findById(String id) {
        return (DockerManager) managerMapper.findById(id);
    }

    @Override
    public void updateById(Map<String,Object> parameter) {
        System.out.println("parameter = " + parameter);
        managerMapper.updateById(parameter);
    }
}
