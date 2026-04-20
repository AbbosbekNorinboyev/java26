package uz.brb.java26.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        Long id,
        String sku,
        String nameRu,
        String nameUz,
        String nameEn,
        String ikpuMxik,
        String barcode,
        Long categoryId,
        String categoryName,
        String uomCode,
        String uomCodeSecondary,
        BigDecimal conversionFactor,
        String productType,
        BigDecimal minStockQty,
        BigDecimal maxStockQty,
        boolean lotTracked,
        boolean serialTracked,
        boolean expiryTracked,
        String notes,
        boolean active,
        LocalDateTime datetimeCreated
) {
}

