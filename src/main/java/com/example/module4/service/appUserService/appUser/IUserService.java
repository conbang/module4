package com.example.module4.service.appUserService.appUser;


import com.example.module4.model.LoginUser;
import com.example.module4.service.IGeneralService;
import com.example.module4.service.jwtService.JwtService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<LoginUser>, UserDetailsService {
    Optional<LoginUser> findByUsername(String username);
}
