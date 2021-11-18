package com.lt.dailytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * @author tong.luo  daily:dailypwd@localhost:8010/login
 * @description SelfSecurityConfig
 * @date 2021/11/15 19:27
 */
@Configuration
public class SelfSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("dailypwd");
        auth.inMemoryAuthentication().withUser("daily").password(encode).roles("admin");
        userDetailsService();
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.getEncode());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                /* .loginPage("/login.html")//登录页面
                 .loginProcessingUrl("")//登录访问路径*/
                 .defaultSuccessUrl("/index.html").permitAll()
                //设置哪些路径不需要访问
                .and().authorizeRequests().mvcMatchers("/", "/layui/**", "/test/testController")
                .permitAll()
                //判断用户存在的权限控制
                /*.antMatchers("").hasAuthority("")
                .antMatchers("").hasRole("")*/
                //关闭csrf防护
                .and().csrf().disable()
        ;
    }

    @Bean
    public PasswordEncoder getEncode() {
        return new BCryptPasswordEncoder();
    }
}
