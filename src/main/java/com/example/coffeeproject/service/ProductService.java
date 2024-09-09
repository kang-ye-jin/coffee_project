package com.example.coffeeproject.service;

import com.example.coffeeproject.dto.ProductDTO;
import com.example.coffeeproject.entity.Product;
import com.example.coffeeproject.exception.ProductException;
import com.example.coffeeproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;

    //상품 등록
    public ProductDTO register(ProductDTO productDTO) {
        try {
            Product product = productDTO.toEntity();
            productRepository.save(product);
            return new ProductDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_REGISTERED.get();
        }
    }

    //상품 조회
    public ProductDTO read(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductException.NOT_FOUND::get);
        return new ProductDTO(product);
    }
}
