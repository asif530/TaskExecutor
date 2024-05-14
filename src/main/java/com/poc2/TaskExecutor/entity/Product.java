package com.poc2.TaskExecutor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "Product", schema = "Production")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String productNumber;

    private boolean makeFlag;

    private boolean finishedGoodsFlag;

    private String color;

    private short safetyStockLevel;

    private short reorderPoint;

    @Column(columnDefinition = "money")
    private BigDecimal standardCost;

    @Column(columnDefinition = "money")
    private BigDecimal listPrice;

    private String size;

    @Column(length = 3)
    private String sizeUnitMeasureCode;

    @Column(length = 3)
    private String weightUnitMeasureCode;

    private BigDecimal weight;

    private Integer daysToManufacture;

    private String productLine;

    @Column(name="Clazz")
    private String productClass;

    private String style;

    private Integer productSubcategoryID;

    private Integer productModelID;

    private LocalDateTime sellStartDate;

    private LocalDateTime sellEndDate;

    private LocalDateTime discontinuedDate;

    @Column(name = "rowguid")
    private UUID rowguid;

    private LocalDateTime modifiedDate;
}
