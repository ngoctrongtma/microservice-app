package com.learningmicroservices.orderservice.service;

import com.learningmicroservices.orderservice.dto.InventoryResponse;
import com.learningmicroservices.orderservice.dto.OrderLineItemDto;
import com.learningmicroservices.orderservice.dto.OrderRequest;
import com.learningmicroservices.orderservice.model.Order;
import com.learningmicroservices.orderservice.model.OrderLineItem;
import com.learningmicroservices.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final WebClient.Builder webClientBuilder;

    private final OrderRepository orderRepository;

    public OrderService(WebClient.Builder webClientBuilder, OrderRepository orderRepository) {
        this.webClientBuilder = webClientBuilder;
        this.orderRepository = orderRepository;
    }

    // create new order
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto)).collect(Collectors.toList());

        order.setOrderLineItemList(orderLineItems);

        List<String> listSkuCodeToCheck = order.getOrderLineItemList().
                stream().
                map(orderLineItem -> orderLineItem.getSkuCode())
                .collect(Collectors.toList());

        // call inventory service to check stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", listSkuCodeToCheck).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductIsInStock = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if(allProductIsInStock){
            orderRepository.save(order);
        } else {
            throw new IllegalStateException("Product is not in stock");
        }

    }

    // convert from DTO to model
    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuanlity(orderLineItemDto.getQuanlity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }


}
