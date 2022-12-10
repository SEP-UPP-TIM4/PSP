package com.example.authservice.service;

import com.example.authservice.model.Role;
import com.example.authservice.repository.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
