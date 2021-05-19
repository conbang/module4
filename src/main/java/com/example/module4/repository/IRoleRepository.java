package com.example.module4.repository;

import com.example.module4.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
