package com.Nivesh.ECommerceWebsite.Entities.Users;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int sellerUserId;
    @Column(name = "GST")
    private String gst;
    @Column(name = "company_contact")
    private long companyContact;
    @Column(name = "company_name")
    private String companyName;

    public int getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(int sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Password")
    private String password;

    @OneToOne(mappedBy = "seller")
    @JoinTable(name="user_seller", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Set<Address> addresses = new HashSet<>();

    public User getUser() {
        return user;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public long getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(long companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
