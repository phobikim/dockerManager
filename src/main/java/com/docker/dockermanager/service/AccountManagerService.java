package com.docker.dockermanager.service;

import com.docker.dockermanager.entity.AccountManager;
import com.docker.dockermanager.repository.AccountManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.AcceptPendingException;
import java.util.List;

@Service
@Transactional
public class AccountManagerService {
    @Autowired
    private AccountManagerRepo accountManagerRepo;

    public AccountManager findById(String id) {
        return accountManagerRepo.findById(id);
    }
}
