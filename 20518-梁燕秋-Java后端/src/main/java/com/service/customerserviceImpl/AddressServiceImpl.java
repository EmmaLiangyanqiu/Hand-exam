package com.service.customerserviceImpl;

import com.entity.Address;
import com.mappers.AddressMapper;
import com.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Emma on 2018/8/26.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> queryAddress() {
        return addressMapper.queryAddress();
    }
}
