package com.Nivesh.ECommerceWebsite.Entities.UserDetails;

public class Role {

    private long id;
    private  String[] authority;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getAuthority() {
        return authority;
    }

    public void setAuthority(String[] authority) {
        this.authority = authority;
    }

    /*  ID
            AUTHORITY*/
}
