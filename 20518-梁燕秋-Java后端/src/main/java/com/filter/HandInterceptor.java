package com.filter;

import com.entity.Customer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Emma on 2018/8/27.
 */
public class HandInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        //未登录，则去登录
        if (customer == null) {
            response.setCharacterEncoding("gb2312");
            PrintWriter out=response.getWriter();
            out.print("<script>alert('请登录！');location.href='../login.html'</script>");
            return false;
        } else {
            return true;
        }

    }
}