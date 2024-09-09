package com.example.coffeeproject.repository;

import com.example.coffeeproject.entity.Category;
import com.example.coffeeproject.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Log4j2
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    //상품 등록 테스트
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            Product product = Product.builder().productName("productName").category(Category.COFFEE_BEAN_PACKAGE).price(5000).description("description").build();

            Product savedProduct = productRepository.save(product);

            assertNotNull(savedProduct);
            assertEquals(i, savedProduct.getProductId());
        });
    }
}
