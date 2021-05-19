package com.example.module4.service.appUserService.userService;

import com.example.module4.model.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILoginUserService {
    List<LoginUser> findALl();

    Page<LoginUser> findALl(Pageable pageable);

    LoginUser findById(Long id);

    LoginUser save(LoginUser loginUser);

    void deleteById(Long id);
}
