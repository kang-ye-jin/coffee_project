package com.example.coffeeproject.controller;

import com.example.coffeeproject.dto.order.OrderResponseDTO;
import com.example.coffeeproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Log4j2
public class OrderController {
    private final OrderService orderService;

    //주문 등록
    @PostMapping
    public ResponseEntity<OrderResponseDTO> register(@RequestBody OrderResponseDTO orderResponseDTO) {
        log.info("Creating order: {}", orderResponseDTO);
        return ResponseEntity.ok(orderService.create(orderResponseDTO));
    }

    //주문 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId) {
        log.info("Get order: {}", orderId);
        return ResponseEntity.ok(orderService.read(orderId));
    }

    //주문 수정
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> modifyOrder(@PathVariable Long orderId, @RequestBody OrderResponseDTO orderResponseDTO) {
        log.info("Update order: {}", orderResponseDTO);
        return ResponseEntity.ok(orderService.update(orderId, orderResponseDTO));
    }

    //주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, String>> removeOrder(@PathVariable Long orderId) {
        log.info("Remove order: {}", orderId);
        orderService.delete(orderId);
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    //주문 리스트
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        log.info("Get all orders");
        return ResponseEntity.ok(orderService.readAll());
    }
}
