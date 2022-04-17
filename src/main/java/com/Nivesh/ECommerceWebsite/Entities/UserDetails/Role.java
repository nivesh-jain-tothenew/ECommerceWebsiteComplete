package com.Nivesh.ECommerceWebsite.Entities.UserDetails;

import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Role  implements GrantedAuthority {

    @Id
    private long id;
    private  String authority;

     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
     private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
