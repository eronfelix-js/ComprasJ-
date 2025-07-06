package com.dev.comprasJa.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductEntity product;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    @ManyToOne
    private Sale sale;
}
