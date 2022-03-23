package com.Nivesh.ECommerceWebsite.Entities.Users;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private long userId;
    @Email
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private boolean isDeleted;
    private boolean isActive;
    private boolean isExpired;
    private boolean isLocked;

    private int INVALID_ATTEMPT_COUNT;
    private Date PASSWORD_UPDATE_DATE;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "add_id", referencedColumnName = "id")
    private Set<Address> addresses = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
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






}
