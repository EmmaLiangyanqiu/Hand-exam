package com.mappers;

import com.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Emma on 2018/8/23.
 */
@Component
public interface CustomerMapper {
    public Customer queryByName(String first_name);
    public Customer queryById(int customer_id);
    public List<Customer> queryAll();
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(int customer_id);
}
