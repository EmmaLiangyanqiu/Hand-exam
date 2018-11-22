package com.mappers;

import com.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Emma on 2018/8/26.
 */
@Component
public interface AddressMapper {
    public List<Address> queryAddress();
}
