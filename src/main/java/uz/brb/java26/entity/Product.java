package uz.brb.java26.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(name = "name_ru", nullable = false)
    private String nameRu;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "ikpu_mxik")
    private String ikpuMxik;

    private String barcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @Column(name = "uom_code", nullable = false)
    private String uomCode;

    @Column(name = "uom_code_secondary")
    private String uomCodeSecondary;

    @Column(name = "conversion_factor")
    private BigDecimal conversionFactor;

    @Column(name = "product_type", nullable = false)
    @Builder.Default
    private String productType = "GOODS";

    @Column(name = "min_stock_qty")
    @Builder.Default
    private BigDecimal minStockQty = BigDecimal.ZERO;

    @Column(name = "max_stock_qty")
    private BigDecimal maxStockQty;

    @Column(name = "is_lot_tracked", nullable = false)
    private boolean lotTracked;

    @Column(name = "is_serial_tracked", nullable = false)
    private boolean serialTracked;

    @Column(name = "is_expiry_tracked", nullable = false)
    private boolean expiryTracked;

    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;
}
