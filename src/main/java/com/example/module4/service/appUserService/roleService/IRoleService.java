package com.example.module4.service.appUserService.roleService;


import com.example.module4.model.Role;
import com.example.module4.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
