package com.Nivesh.ECommerceWebsite.Entities.Product;

public class ProductVariation {

    private Integer quantityAvailable;
    private long price;
    private String primaryImageName;
    private boolean isActive;

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    /* ID
PRODUCT_ID
QUANTITY_AVAILABLE
PRICE
"""METADATA (Type: JSON - available in mysql to store a JSON as it is.)
(Note: will contain all the information regarding variations in flat JSON format)
(All variations of same category will have a fixed similar JSON structure.
The fields for the json should be picked from CATEGORY table)"""
PRIMARY_IMAGE_NAME
"IS_ACTIVE
(NEW FIELD)"*/
}
