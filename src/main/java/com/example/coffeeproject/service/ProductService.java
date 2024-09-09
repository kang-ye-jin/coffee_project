package com.example.coffeeproject.service;

import com.example.coffeeproject.dto.ProductDTO;
import com.example.coffeeproject.entity.Product;
import com.example.coffeeproject.exception.ProductException;
import com.example.coffeeproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //상품 수정
    public ProductDTO modify(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId).orElseThrow(ProductException.NOT_FOUND::get);

        try {
            product.changeProductName(productDTO.getProductName());
            product.changeCategory(productDTO.getCategory());
            product.changePrice(productDTO.getPrice());
            product.changeDescription(productDTO.getDescription());
            return new ProductDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_MODIFIED.get();
        }
    }

    //상품 삭제
    public ProductDTO remove(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductException.NOT_FOUND::get);

        try {
            productRepository.delete(product);
            return new ProductDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_REMOVED.get();
        }
    }

    //상품 리스트
    public List<Product> readAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
