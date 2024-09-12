package com.example.coffeeproject.service;

import com.example.coffeeproject.dto.page.PageRequestDTO;
import com.example.coffeeproject.dto.product.ProductResponseDTO;
import com.example.coffeeproject.entity.Product;
import com.example.coffeeproject.exception.ProductException;
import com.example.coffeeproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ProductResponseDTO register(ProductResponseDTO productResponseDTO) {
        try {
            Product product = productResponseDTO.toEntity();
            productRepository.save(product);
            return new ProductResponseDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_REGISTERED.get();
        }
    }

    //상품 조회
    public ProductResponseDTO read(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductException.NOT_FOUND::get);
        return new ProductResponseDTO(product);
    }

    //상품 수정
    public ProductResponseDTO modify(Long productId, ProductResponseDTO productResponseDTO) {
        Product product = productRepository.findById(productId).orElseThrow(ProductException.NOT_FOUND::get);

        try {
            product.changeProductName(productResponseDTO.getProductName());
            product.changeCategory(productResponseDTO.getCategory());
            product.changePrice(productResponseDTO.getPrice());
            product.changeDescription(productResponseDTO.getDescription());
            return new ProductResponseDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_MODIFIED.get();
        }
    }

    //상품 삭제
    public ProductResponseDTO remove(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductException.NOT_FOUND::get);

        try {
            productRepository.delete(product);
            return new ProductResponseDTO(product);
        } catch (Exception e) {
            log.error("error : " + e);
            throw ProductException.NOT_REMOVED.get();
        }
    }

    //상품 리스트
    public Page<Product> readAll(PageRequestDTO pageRequestDTO) {
        Sort sort = Sort.by("productId").ascending();
        Pageable pageable = pageRequestDTO.getPageable(sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }
}
