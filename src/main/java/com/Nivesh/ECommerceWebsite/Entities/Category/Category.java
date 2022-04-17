package com.Nivesh.ECommerceWebsite.Entities.Category;

import com.Nivesh.ECommerceWebsite.Entities.Product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    private int id;
    private String name;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ID
            NAME
    PARENT_CATEGORY_ID (This is category directly preceding this category) */
}
