package com.example.coffeeproject.dto.order;

import com.example.coffeeproject.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemResponseDTO {
    private Long productId;
    private String category;
    private int price;
    private int quantity;

    public OrderItemResponseDTO(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getProductId();
        this.category = orderItem.getProduct().getCategory().name();
        this.price = orderItem.getProduct().getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
