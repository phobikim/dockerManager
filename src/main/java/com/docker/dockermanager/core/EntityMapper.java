package com.docker.dockermanager.core;

import com.docker.dockermanager.entity.DockerManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntityMapper<T extends DockerManager> {
    //table = dockerManger
    public List<T> searchAll();
    public void updateState(T entity);

    public T findById(String id);
    public void updateById(Map<String,Object> parameter);

}
