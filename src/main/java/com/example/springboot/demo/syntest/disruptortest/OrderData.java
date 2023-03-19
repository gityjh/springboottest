package com.example.springboot.demo.syntest.disruptortest;

/**
 * 订单数据
 */
public class OrderData {
    private String id;
    private String orderNumber;
    private Integer price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
