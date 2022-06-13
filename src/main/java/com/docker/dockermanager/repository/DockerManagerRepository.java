package com.docker.dockermanager.repository;

import com.docker.dockermanager.core.EntityMapper;
import com.docker.dockermanager.entity.DockerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DockerManagerRepository implements EntityMapper<DockerManager> {

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<DockerManager> searchAll() {
        return entityMapper.searchAll();
    }

    @Override
    public void updateState(DockerManager entity) {
        entityMapper.updateState(entity);
    }

    @Override
    public DockerManager findById(String id) {
        return entityMapper.findById(id);
    }

    @Override
    public void updateById(Map<String,Object> parameter) {
        System.out.println("parameter = " + parameter);
        entityMapper.updateById(parameter);
    }
}
