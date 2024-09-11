package com.example.coffeeproject.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequestDTO {
    private Long productId;
    private int quantity;
}
