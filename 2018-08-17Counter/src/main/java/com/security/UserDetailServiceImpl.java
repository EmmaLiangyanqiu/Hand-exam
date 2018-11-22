package com.security;

import com.entity.User;
import com.mappers.UserMapper;
import com.service.UserService;
import com.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Emma on 2018/8/22.
 */

/**
 *通过MyUserDetailServiceImpl拿到用户信息后，authenticationManager对比用户的密码（即验证用户）
 *
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 获得对象的方式：
 * WebUserDetails webUserDetails =
 * (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 *
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 *
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user=userMapper.findByname(username);
        System.out.println("==========================UserServiceDetail========");
        System.out.println(user);

        Collection<GrantedAuthority> grantedAuthorities=obtionGrantedAuthorities(user);
        org.springframework.security.core.userdetails.User userDetail=
                new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),true,true,
                        true,true,grantedAuthorities);

        return userDetail;
    }

    /**
     *取得用户的权限
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user){
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

            // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
            authSet.add(new SimpleGrantedAuthority(user.getRole()));
        return authSet;
    }
}
