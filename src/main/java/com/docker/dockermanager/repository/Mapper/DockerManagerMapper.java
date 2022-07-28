package com.docker.dockermanager.repository.Mapper;

import com.docker.dockermanager.entity.DockerManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DockerManagerMapper extends EntityMapper<DockerManager> {
    @Override
    List<DockerManager> searchAll();

    @Override
    void updateState(DockerManager entity);

    @Override
    DockerManager findById(String id);

    @Override
    void updateById(Map<String, Object> parameter);
}
