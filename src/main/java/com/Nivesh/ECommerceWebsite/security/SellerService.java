package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.SellerDTO;

import java.util.List;

public interface SellerService {

    public List<SellerDTO> getSellers();

    SellerDTO sellerConvertEntityToDto(Seller Seller);

    Seller sellerConvertDtoToEntity(SellerDTO SellerDTO);

    public SellerDTO saveSeller(SellerDTO SellerDTO);

    void sendEmail(User user);
}
