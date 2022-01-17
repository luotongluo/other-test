package com.lt.dailytest.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author tong.luo
 * @description SelfUserDetailsService
 * @date 2021/11/16 11:33
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {
    private static HashMap<String, String> staticMap = new HashMap<>();

    static {
        staticMap.put("daily", "dailypwd");
        staticMap.put("admin", "pwd");
        staticMap.put("123", "333");
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("roles", "admin", "admins");
        String key = "daily";
        //可以将下面的固定值修改为从数据库中查询
        return new User(key, new BCryptPasswordEncoder().encode(staticMap.get(key)), grantedAuthorities);
    }
}
