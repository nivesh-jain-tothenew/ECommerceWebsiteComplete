package com.Nivesh.ECommerceWebsite.Entities.Product;

import com.Nivesh.ECommerceWebsite.Entities.Category.Category;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    private long id;
    private String name;
    private String description;
    private boolean isCancellable;
    private boolean isReturnable;
    private String brand;
    private boolean isActive;
    private boolean isDeleted;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;
    //@OneToMany(mappedBy = "product")
    @ManyToOne
    @JoinColumn(name = "product_variation_product_variation_id")
    private ProductVariation productVariation;

    public ProductVariation getProductVariation() {
        return productVariation;
    }

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

    public boolean getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(boolean isReturnable) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
