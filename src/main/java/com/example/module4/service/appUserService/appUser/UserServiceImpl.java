package com.example.module4.service.appUserService.appUser;

import com.example.module4.model.LoginUser;
import com.example.module4.model.UserPrinciple;
import com.example.module4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<LoginUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<LoginUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public LoginUser save(LoginUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginUser> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public Optional<LoginUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}