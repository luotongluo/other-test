package com.lt.conbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author tong.luo  con-base:con-base123@localhost:8010/login
 * @description SelfSecurityConfig
 * @date 2021/11/15 19:27
 */
@Configuration
@Component
public class SelfSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("pwd");
        auth.inMemoryAuthentication().withUser("conbase").password(encode).roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")//登录页面
                .loginProcessingUrl("")//登录访问路径
                .defaultSuccessUrl("").permitAll()
                //设置哪些路径不需要访问
                .and().authorizeRequests().mvcMatchers("/", "/test/testController").permitAll()
                //关闭csrf防护
                .and().csrf().disable()
        ;
    }

    @Bean
    public PasswordEncoder getEncode() {
        return new BCryptPasswordEncoder();
    }
}
