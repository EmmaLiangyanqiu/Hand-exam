package com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Emma on 2018/8/18.
 */
@Controller
public class CounterController {
    @Autowired
    UserService userService;


    @RequestMapping("/count")
    public String count(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("count");
        System.out.println(userService.findByid(1));
        return "index";

    }


    /*
    * 登陆
    * */
    @RequestMapping("/login")
    public String userLogin(){
        return null;
    }

    /*
    *分页查询测试
     */
    @RequestMapping("/queryAll")
    public String qurey(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn,
                        HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PageHelper.startPage(pn, 2);
        List<User> userList = userService.queryAll();
        PageInfo<User> pagehelper = new PageInfo<User>(userList, 5);
        for (User user : userList) {
            System.out.println(user.getUsername());
        }
        session.setAttribute("pagehelper", pagehelper);

        return "list_page";
    }

    /*
    * 增加数据
    * */

    @RequestMapping("/addUser")
    public String addUser(HttpServletResponse response, HttpServletRequest request) {
        User user = new User();
        user.setUsername("王维");
        userService.addUser(user);
        System.out.println("添加成功");
        return "success";
    }

    /*
    * 更新数据
    * */
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletResponse response, HttpServletRequest request) {
        User user = new User();
        user.setUsername("李煜");
        user.setId(11);
        userService.updateUser(user);
        System.out.println("更新成功");
        return "success";
    }

    /*
    * 删除数据
    * */
    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletResponse response, HttpServletRequest request) {
        userService.deleteUser(12);
        System.out.println("删除成功");
        return "success";
    }
    /*
    * 测试接口
    * */
  @RequestMapping("/test")
    public void test(){
      System.out.println(userService.findByname("李白").getPassword());
  }

}
