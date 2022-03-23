package com.Nivesh.ECommerceWebsite.Entities.Users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class seller extends User{

    private String gst;
    private Integer companyContact;
    private String companyName;

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public Integer getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(Integer companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
