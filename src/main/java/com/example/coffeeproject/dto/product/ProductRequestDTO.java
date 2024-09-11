package com.example.coffeeproject.dto.product;

import com.example.coffeeproject.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank
    private String productName;

    private Category category;

    @Min(0)
    private int price;

    private String description;
}
