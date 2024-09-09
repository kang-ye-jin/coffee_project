package com.example.coffeeproject.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequestDTO {
    @NotEmpty
    private Long productId;
    @NotEmpty
    private int quantity;
}
