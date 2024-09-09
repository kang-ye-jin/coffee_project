package com.example.coffeeproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name="orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    private String email;

    @NotNull
    private String address;

    @NotNull
    private int postcode;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatus orderStatus;

    public void changeAddress(String address) {
        this.address = address;
    }

    public void changePostcode(int postcode) {
        this.postcode = postcode;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
