package com.Nivesh.ECommerceWebsite.Entities.dto;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

public class CustomerDTO {

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone number not vaild")
    long contactNumber;
    @Valid
    UserDTO userDTO;

    public CustomerDTO(long contactNumber, UserDTO userDTO) {
        this.contactNumber = contactNumber;
        this.userDTO = userDTO;
    }


    public CustomerDTO(long contactNumber) {
        this.contactNumber = contactNumber;
    }
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
