package uz.brb.java26.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank
    private String sku;
    @NotBlank
    private String nameRu;
    private String nameUz;
    private String nameEn;
    private String ikpuMxik;
    private String barcode;
    private Long categoryId;
    @NotBlank
    private String uomCode;
    private String uomCodeSecondary;
    private BigDecimal conversionFactor;
    private String productType;
    private BigDecimal minStockQty;
    private BigDecimal maxStockQty;
    private boolean lotTracked;
    private boolean serialTracked;
    private boolean expiryTracked;
    private String notes;
    private Boolean active;
}
