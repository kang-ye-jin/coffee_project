package com.example.coffeeproject.dto;

import com.example.coffeeproject.entity.Category;
import com.example.coffeeproject.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDTO {
    private Long productId;

    @NotBlank
    private String productName;

    private Category category;

    @Min(0)
    private int price;

    private String description;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public Product toEntity() {
        Product product = Product.builder().productId(productId).productName(productName).category(category).price(price).description(description).build();
        return product;
    }
}
