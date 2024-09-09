package com.example.coffeeproject.service;

import com.example.coffeeproject.dto.order.OrderItemResponseDTO;
import com.example.coffeeproject.dto.order.OrderResponseDTO;
import com.example.coffeeproject.entity.Order;
import com.example.coffeeproject.entity.OrderItem;
import com.example.coffeeproject.entity.Product;
import com.example.coffeeproject.exception.OrderException;
import com.example.coffeeproject.exception.ProductException;
import com.example.coffeeproject.repository.OrderRepository;
import com.example.coffeeproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDTO create(OrderResponseDTO orderResponseDTO) {
        try {
            Order order = orderResponseDTO.toEntity();

            List<OrderItem> orderItems = new ArrayList<>();

            for (OrderItemResponseDTO orderItemResponseDTO : orderResponseDTO.getOrderItems()) {
                Product product = productRepository.findById(orderItemResponseDTO.getProductId()).orElseThrow(ProductException.NOT_FOUND::get);

                OrderItem orderItem = OrderItem.builder()
                        .product(product)
                        .order(order)
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .quantity(orderItemResponseDTO.getQuantity())
                        .build();

                orderItems.add(orderItem);
            }

            order.setOrderItems(orderItems);

            orderRepository.save(order);

            return new OrderResponseDTO(order);
        } catch (Exception e) {
            log.error(e);
            throw OrderException.NOT_REGISTERED.get();
        }
    }

    //주문 조회
    public OrderResponseDTO read(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderException.NOT_FOUND::get);

        return new OrderResponseDTO(order);
    }

    //주문 수정
    public OrderResponseDTO update(Long id, OrderResponseDTO orderResponseDTO) {
        Order order = orderRepository.findById(id).orElseThrow(OrderException.NOT_FOUND::get);

        try {
            order.changeAddress(orderResponseDTO.getAddress());
            order.changePostcode(orderResponseDTO.getPostcode());
            order.changeOrderStatus(orderResponseDTO.getOrderStatus());

            return new OrderResponseDTO(order);
        } catch (Exception e) {
            log.error(e);
            throw OrderException.NOT_MODIFIED.get();
        }
    }

    //주문 삭제
    public OrderResponseDTO delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderException.NOT_FOUND::get);

        try {
            orderRepository.delete(order);

            return new OrderResponseDTO(order);
        } catch (Exception e) {
            log.error(e);
            throw OrderException.NOT_REMOVED.get();
        }
    }

    //주문 리스트
    public List<OrderResponseDTO> readAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();

        for (Order order : orders) {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order);
            orderResponseDTOs.add(orderResponseDTO);
        }

        return orderResponseDTOs;
    }
}
