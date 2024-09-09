package com.example.coffeeproject.dto.order;

import com.example.coffeeproject.entity.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDTO {
    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private int postcode;

    @NotEmpty
    private List<OrderItemRequestDTO> orderItems;

    @NotEmpty
    private OrderStatus orderStatus;
}
