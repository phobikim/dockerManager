package com.docker.dockermanager.repository;

import com.docker.dockermanager.entity.AccountManager;
import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.repository.Mapper.AccountManagerMapper;
import com.docker.dockermanager.repository.Mapper.DockerManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AccountManagerRepoDetail implements AccountManagerRepo {
    @Autowired
    AccountManagerMapper managerMapper;
    @Override
    public List<AccountManager> searchAll() {
        return null;
    }

    @Override
    public void updateState(AccountManager entity) {

    }

    @Override
    public AccountManager findById(String id) {
        return (AccountManager) managerMapper.findById(id);
    }

    @Override
    public void updateById(Map<String, Object> parameter) {

    }
}
