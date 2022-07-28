package com.docker.dockermanager.repository;

import com.docker.dockermanager.entity.AccountManager;
import com.docker.dockermanager.repository.Mapper.AccountManagerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface AccountManagerRepo extends AccountManagerMapper {
    @Override
    List<AccountManager> searchAll();

    @Override
    void updateState(AccountManager entity);

    @Override
    AccountManager findById(String id);

    @Override
    void updateById(Map<String, Object> parameter);
}
