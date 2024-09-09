package com.example.coffeeproject.controller;

import com.example.coffeeproject.dto.ProductDTO;
import com.example.coffeeproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Log4j2
public class ProductController {
    private final ProductService productService;

    //상품 등록
    @PostMapping
    public ResponseEntity<ProductDTO> register(@RequestBody ProductDTO productDTO) {
        log.info("Registering new product");
        return ResponseEntity.ok(productService.register(productDTO));
    }

    //상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> read(@PathVariable Long productId) {
        log.info("Getting product by ID");
        return ResponseEntity.ok(productService.read(productId));
    }
}
