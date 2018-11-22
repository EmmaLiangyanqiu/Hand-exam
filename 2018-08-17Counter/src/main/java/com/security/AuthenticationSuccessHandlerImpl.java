package com.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Emma on 2018/8/21.
 */

/*
* 通过 authentication-success-handler-ref 指定authentication-success-handler-ref 对应一个 AuthencticationSuccessHandler
 * 实现类的引用。如果指定了 authentication-success-handler-ref，登录认证成功后会调用指定 AuthenticationSuccessHandler
 * 的 onAuthenticationSuccess 方法。我们需要在该方法体内对认证成功做一个处理，然后返回对应的认证成功页面。使用了
  * authentication-success-handler-ref 之后认证成功后的处理就由指定的 AuthenticationSuccessHandler 来处理，
  * 之前的那些 default-target-url 之类的就都不起作用了。以下是自定义的一个 AuthenticationSuccessHandler 的实现类。
*
* */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("认证成功");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
    }
}
