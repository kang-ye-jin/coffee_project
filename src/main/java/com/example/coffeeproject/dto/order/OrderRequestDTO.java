package com.example.coffeeproject.dto.order;

import com.example.coffeeproject.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String address;

    private int postcode;

    @NotEmpty
    private List<OrderItemRequestDTO> orderItems;

    private OrderStatus orderStatus;
}
