package com.Nivesh.ECommerceWebsite.Entities.Order;

public class OrderProduct {

    private Long quantity;
    private Long price;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    /* ID
ORDER_ID
QUANTITY
PRICE
PRODUCT_VARIATION_ID*/
}
