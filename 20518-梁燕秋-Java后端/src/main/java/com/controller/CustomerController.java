package com.controller;

import com.entity.Customer;
import com.entity.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emma on 2018/8/23.
 */
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;


    /*
    * 登陆
    * */
    @RequestMapping( "/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        String first_name=request.getParameter("username");
        String last_name=request.getParameter("password");
        Customer customer=customerService.queryByName(first_name);
        if(customer==null|| !customer.getLast_name().equals(last_name)) {
            return "login";
        }else {
            session.setAttribute("customer",customer);
            return "index";
        }
    }

/*
* 查询分页
* */
    @RequestMapping(value = "/queryAll",method =RequestMethod.GET )
    @ResponseBody
    public PageInfo queryAll(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn,
                          HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        PageHelper.startPage(pn, 4);
        List<Customer> customerList=customerService.queryAll();
        PageInfo pageInfo = new PageInfo(customerList, 5);
        return pageInfo ;
    }

    /*
    * 检验用户名是否存在
    * */
    @RequestMapping("/checkuser")
    @ResponseBody
    public boolean checkUser(@RequestParam String first_name) {
        if (customerService.queryByName(first_name)==null){
            return true;
        }else {
            return false;
        }
    }
    /*
    * 添加
    * */
    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    @ResponseBody
    public Boolean addCustomer(Customer customer, HttpServletRequest request, HttpServletResponse response) {
        customer.setStore_id(1);
        customer.setCreate_date(getTime());
        customer.setLast_update(getTime());
        customer.setActive(1);
        customerService.addCustomer(customer);
        return true;
    }


    /*
    * 删除
    * */
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public String deleteCustomer(@RequestParam int customer_id, HttpServletRequest request, HttpServletResponse response) {
        customerService.deleteCustomer(customer_id);
        return "success";
    }

    /*
    * 获取数据回显
    * */
    @RequestMapping("/getCustomer")
    @ResponseBody
    public Customer getCustomer(int customer_id, HttpServletRequest request, HttpServletResponse response){
        return customerService.queryById(customer_id);
    }

    /*
    * 更新
    * */

    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    @ResponseBody
    public String updateCustomer(Customer customer, int customer_id,HttpServletRequest request, HttpServletResponse response) {
        customer.setCustomer_id(customer_id);
        customer.setLast_update(getTime());
        customerService.updateCustomer(customer);
        return "update success";
    }
    @RequestMapping("/test")
    public String test(){
        System.out.println("test");
        return null;
    }

    /*
* 获取时间
* */
    public String getTime(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(currentTime);
        return time;
    }

    /*
      * 注销账号
      * */
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        session.invalidate();
        response.setCharacterEncoding("gb2312");
        PrintWriter out=response.getWriter();
        out.print("<script>alert('退出系统！');location.href='../login.html'</script>");

    }

}
