package com.docker.dockermanager.repository.Mapper;

import com.docker.dockermanager.entity.AccountManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountManagerMapper extends EntityMapper<AccountManager> {
    @Override
    List<AccountManager> searchAll();

    @Override
    void updateState(AccountManager entity);

    @Override
    AccountManager findById(String id);

    @Override
    void updateById(Map<String, Object> parameter);
}
