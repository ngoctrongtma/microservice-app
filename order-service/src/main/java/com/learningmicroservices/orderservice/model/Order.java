package com.learningmicroservices.orderservice.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;

    public Order() {
    }

    public Order(Long id, String orderNumber, List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItems;
    }

    public void setOrderLineItemList(List<OrderLineItem> orderLineItemList) {
        this.orderLineItems = orderLineItemList;
    }
}
