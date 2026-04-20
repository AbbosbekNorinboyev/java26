package uz.brb.java26.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.java26.dto.request.ProductRequest;
import uz.brb.java26.dto.response.ProductResponse;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.Product;
import uz.brb.java26.entity.ProductCategory;
import uz.brb.java26.exception.ResourceNotFoundException;
import uz.brb.java26.repository.ProductCategoryRepository;
import uz.brb.java26.repository.ProductRepository;
import uz.brb.java26.service.ProductService;

import java.time.LocalDateTime;

import static uz.brb.java26.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Response<?> create(ProductRequest request) {
        ProductCategory category = productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        Product product = Product.builder()
                .sku(request.getSku())
                .nameRu(request.getNameRu())
                .nameUz(request.getNameUz())
                .nameEn(request.getNameEn())
                .ikpuMxik(request.getIkpuMxik())
                .barcode(request.getBarcode())
                .category(category)
                .uomCode(request.getUomCode())
                .uomCodeSecondary(request.getUomCodeSecondary())
                .conversionFactor(request.getConversionFactor())
                .productType(request.getProductType())
                .minStockQty(request.getMinStockQty())
                .maxStockQty(request.getMaxStockQty())
                .lotTracked(true)
                .serialTracked(true)
                .expiryTracked(true)
                .notes(request.getNotes())
                .active(true)
                .build();
        productRepository.save(product);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Product successfully save")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    @Override
    public Response<?> lookup(String code) {
        Product product = productRepository.findByBarcodeOrSkuIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found by barcode or SKU: " + code));
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .nameRu(product.getNameRu())
                .nameUz(product.getNameUz())
                .nameEn(product.getNameEn())
                .ikpuMxik(product.getIkpuMxik())
                .barcode(product.getBarcode())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory().getNameUz())
                .uomCode(product.getUomCode())
                .uomCodeSecondary(product.getUomCodeSecondary())
                .conversionFactor(product.getConversionFactor())
                .productType(product.getProductType())
                .minStockQty(product.getMinStockQty())
                .maxStockQty(product.getMaxStockQty())
                .lotTracked(product.isLotTracked())
                .serialTracked(product.isSerialTracked())
                .expiryTracked(product.isExpiryTracked())
                .notes(product.getNotes())
                .active(product.isActive())
                .build();
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .data(response)
                .message("Product successfully save")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
