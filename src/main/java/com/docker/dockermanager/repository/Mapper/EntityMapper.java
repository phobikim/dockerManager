package com.docker.dockermanager.repository.Mapper;

import com.docker.dockermanager.entity.Entity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


public interface EntityMapper<T extends Entity> {
    //table = dockerManger
    public List<T> searchAll();
    public void updateState(T entity);

    public T findById(String id);
    public void updateById(Map<String,Object> parameter);

}
