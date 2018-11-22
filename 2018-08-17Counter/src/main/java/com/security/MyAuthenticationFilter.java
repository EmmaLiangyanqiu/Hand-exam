package com.security;

import com.entity.User;
import com.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Emma on 2018/8/22.
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
         private static final String USERNAME="username";
         private static final String PASSWORD="password";

         @Autowired
         private UserMapper userMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "
                            + request.getMethod());
        }

        String username=obtainUsername(request);
        String password=obtainPassword(request);
        username=username.trim();

        UsernamePasswordAuthenticationToken authRequest = null;
        User user=userMapper.findByname(username);
        System.out.println("=========================myfilter===================");

       if (user==null){
           UsernameNotFoundException exception=new UsernameNotFoundException("用戶名不存在,请注册");
           throw exception;
       }
       else if (!password.equals(user.getPassword())){
           BadCredentialsException exception = new BadCredentialsException(
                   "用户名或密码不匹配,请重新输入");
           throw exception;
       }

        // 实现验证
        authRequest = new UsernamePasswordAuthenticationToken(username,
                password);
        // 允许设置用户详细属性
        setDetails(request, authRequest);
        // 运行
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object object=request.getParameter(PASSWORD);
        return null ==object ? "":object.toString();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object object=request.getParameter(USERNAME);
        return null == object ? "" : object.toString();
    }

    @Override
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        super.setDetails(request, authRequest);
    }
}
