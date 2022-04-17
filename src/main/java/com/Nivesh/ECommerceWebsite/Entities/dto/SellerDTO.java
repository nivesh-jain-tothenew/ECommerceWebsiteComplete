package com.Nivesh.ECommerceWebsite.Entities.dto;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

public class SellerDTO {

    @Email(message = "email not valid", regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @NotNull
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "password must be atleast eight characters, at least one uppercase letter, one lowercase letter, one number and one special character.")
    @NotBlank(message = "password is mandatory")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    @Pattern(regexp = "/^[a-zA-Z0-9]+$/", message = "gst number not valid")
    @NotBlank(message = "gst number not filled")
    private String gst;

    @Valid
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Set<AddressDTO> getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(Set<AddressDTO> addressDTO) {
        this.addressDTO = addressDTO;
    }

    @Valid
    private Set<AddressDTO> addressDTO;

    @NotBlank(message = "company name required")
    private String companyName;

    @NotBlank(message = "company address required")
    private String companyAddress;

    @NotBlank(message = "contact number required")
    @Size(min = 10, max = 10, message = "phone number not valid")
    private long companyContact;

    @NotBlank(message = "name required")
    @Size(max = 15, message = "first name too long")
    private String firstName;

    @NotBlank(message = "first name required")
    @Size(max = 15, message = "last name too long")
    private String lastName;

    private Set<Role> roles;

    private Date passwordUpdateDate;

    public SellerDTO(String gst, long companyContact, String companyName, UserDTO userDTO, Set<AddressDTO> addressDTO) {
        this.gst = gst;
        this.companyContact = companyContact;
        this.companyName = companyName;
        this.userDTO = userDTO;
        this.addressDTO = addressDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public long getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(long companyContact) {
        this.companyContact = companyContact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getPasswordUpdateDate() {
        return passwordUpdateDate;
    }

    public void setPasswordUpdateDate(Date passwordUpdateDate) {
        this.passwordUpdateDate = passwordUpdateDate;
    }
}
