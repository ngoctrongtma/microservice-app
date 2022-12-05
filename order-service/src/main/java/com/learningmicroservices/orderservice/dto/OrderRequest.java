package com.learningmicroservices.orderservice.dto;

import java.util.List;


public class OrderRequest {
    private List<OrderLineItemDto> orderLineItemDtoList;

    public OrderRequest(List<OrderLineItemDto> orderLineItemDtoList) {
        this.orderLineItemDtoList = orderLineItemDtoList;
    }

    public OrderRequest() {
    }

    public List<OrderLineItemDto> getOrderLineItemDtoList() {
        return orderLineItemDtoList;
    }

    public void setOrderLineItemDtoList(List<OrderLineItemDto> orderLineItemDtoList) {
        this.orderLineItemDtoList = orderLineItemDtoList;
    }
}
