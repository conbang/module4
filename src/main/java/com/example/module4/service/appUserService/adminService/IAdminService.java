package com.example.module4.service.appUserService.adminService;

import com.example.module4.model.LoginUser;
import com.example.module4.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminService {
//    Page<LoginUser> findAll(Pageable pageable);
//    Page<LoginUser> findUserRoleName(LoginUser loginUser, Pageable pageable);
Page<LoginUser> findLoginUserByRoleId(Long id, Pageable pageable);
}
