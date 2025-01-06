package com.example.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Product {
    private String id;
    private String name;
    private Double price;
    private int quantity;
}
