package com.controller;

import com.entity.Address;
import com.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Emma on 2018/8/26.
 */
@Controller
public class AddressController {
    @Autowired
    AddressService addressService;


    /*
   * 查询全部地址
   * */
    @RequestMapping("/queryAddress")
    @ResponseBody
    public List<Address> queryAddress(){
        return addressService.queryAddress();
    }
}
