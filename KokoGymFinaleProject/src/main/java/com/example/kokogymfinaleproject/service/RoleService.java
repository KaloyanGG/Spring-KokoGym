package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.entity.RoleEntity;
import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import com.example.kokogymfinaleproject.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void init() {

        if(roleRepository.count() == 0){
            roleRepository.save(new RoleEntity(RoleEnum.BOSS));
            roleRepository.save(new RoleEntity(RoleEnum.TRAINER));
            roleRepository.save(new RoleEntity(RoleEnum.CUSTOMER));
        }

    }
}
