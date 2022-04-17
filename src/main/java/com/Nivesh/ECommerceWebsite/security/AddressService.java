package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.dto.AddressDTO;

import java.util.Set;

public interface AddressService {

    Address addressConvertDtoToEntity(AddressDTO addressDTO);

    Set<AddressDTO> addressConvertEntityTODto(Set<Address> address);
}
