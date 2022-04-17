package com.Nivesh.ECommerceWebsite.Entities.Users;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "id")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Email
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Password")
    private String password;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_expired")
    private boolean isExpired;
    @Column(name = "is_locked")
    private boolean isLocked;
    private int INVALID_ATTEMPT_COUNT;
    private Date PASSWORD_UPDATE_DATE;
    private String confirmPassword;
    private long contactNumber;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Seller seller;

    @OneToOne
    private Customer customer;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    Set<Address> addresses = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDeleted(boolean b) {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        this.isExpired = expired;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    public int getINVALID_ATTEMPT_COUNT() {
        return INVALID_ATTEMPT_COUNT;
    }

    public void setINVALID_ATTEMPT_COUNT(int INVALID_ATTEMPT_COUNT) {
        this.INVALID_ATTEMPT_COUNT = INVALID_ATTEMPT_COUNT;
    }

    public Date getPASSWORD_UPDATE_DATE() {
        return PASSWORD_UPDATE_DATE;
    }

    public void setPASSWORD_UPDATE_DATE(Date PASSWORD_UPDATE_DATE) {
        this.PASSWORD_UPDATE_DATE = PASSWORD_UPDATE_DATE;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }
}
