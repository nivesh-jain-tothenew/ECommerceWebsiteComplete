package com.Nivesh.ECommerceWebsite.Entities.Product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private long id;
    private String name;
    private String description;
    private boolean isCancellable;
    private String isReturnable;
    private String brand;
    private boolean isActive;
    private boolean isDeleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.isCancellable = cancellable;
    }

    public String getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(String isReturnable) {
        this.isReturnable = isReturnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    /* ID
SELLER_USER_ID
NAME
DESCRIPTION
"CATEGORY_ID
* Only the leaf category node would be associated with a product"
IS_CANCELLABLE
IS_RETURNABLE
BRAND
IS_ACTIVE
IS_DELETED*/
}
