package com.example.authservice.service;

import com.example.authservice.model.Role;
import com.example.authservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent())
            return role.get();
        return null;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
