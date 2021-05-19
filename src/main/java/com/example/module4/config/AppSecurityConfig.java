package com.example.module4.config;


import com.example.module4.Module4Application;
import com.example.module4.service.appUserService.appUser.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Autowired
    private IUserService appUserService;

    @Autowired
    private LoginSuccessHandle loginSuccessHandler;

    //lay du lieu user tu trong DB
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    //phan quyen theo tung tai khoan
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/home/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/v1/logot").permitAll()
                .antMatchers("/api/v1/currentUser").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/api/v1/users/**").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/api/v1/shop/**").hasRole("SHOP")
                .and()
                .authorizeRequests().antMatchers("/api/v1/cart/GetCart").hasAnyRole("USER")
                .and()
                .formLogin()
                .and()
                .formLogin().successHandler(loginSuccessHandler)
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedPage("/khongcoquyen");
        http.csrf().disable();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }

}

