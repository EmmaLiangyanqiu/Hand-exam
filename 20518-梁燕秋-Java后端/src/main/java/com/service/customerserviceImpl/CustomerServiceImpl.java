package com.service.customerserviceImpl;

import com.entity.Customer;
import com.mappers.CustomerMapper;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Emma on 2018/8/23.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Customer queryByName(String first_name) {
        return customerMapper.queryByName(first_name);
    }

    @Override
    public Customer queryById(int customer_id) {
        return customerMapper.queryById(customer_id);
    }

    @Override
    public List<Customer> queryAll() {
        return customerMapper.queryAll();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
       /* customerMapper.updateCustomer(customer.getFirst_name(),customer.getLast_name(),customer.getEmail()
        ,customer.getAddress_id());*/
       customerMapper.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customer_id) {
        customerMapper.deleteCustomer(customer_id);
    }
}
