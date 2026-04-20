package uz.brb.java26.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.java26.dto.request.ProductCategoryRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.ProductCategory;
import uz.brb.java26.exception.ResourceNotFoundException;
import uz.brb.java26.repository.ProductCategoryRepository;
import uz.brb.java26.service.ProductCategoryService;

import java.time.LocalDateTime;

import static uz.brb.java26.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Response<?> create(ProductCategoryRequest request) {
        ProductCategory parent = null;

        // faqat parentId bo‘lsa ishlaydi
        if (request.getParentId() != null) {
            parent = productCategoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
        }

        ProductCategory productCategory = ProductCategory.builder()
                .nameRu(request.getNameRu())
                .nameUz(request.getNameUz())
                .parent(parent)
                .sortOrder(request.getSortOrder())
                .active(false)
                .build();
        productCategoryRepository.save(productCategory);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Product category successfully created")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
