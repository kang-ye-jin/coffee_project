package com.example.coffeeproject.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemRequestDTO {
    private Long productId;
    private int quantity;
}
