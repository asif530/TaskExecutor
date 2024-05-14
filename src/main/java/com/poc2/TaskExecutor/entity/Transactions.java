package com.poc2.TaskExecutor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TransactionHistory", schema = "Production")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    private int productID;

    private Integer referenceOrderID; // nullable since it might be null

    private Integer referenceOrderLineID; // nullable since it might be null

    @Column
    private LocalDateTime transactionDate;

    @Column
    private String transactionType;

    private Integer quantity;

    @Column(columnDefinition = "money")
    private BigDecimal actualCost;

    @Column
    private LocalDateTime modifiedDate;
}
