package uz.brb.java26.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.brb.java26.dto.request.ProductCategoryRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.service.ProductCategoryService;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping
    Response<?> create(@RequestBody ProductCategoryRequest request) {
        return productCategoryService.create(request);
    }

    @GetMapping
    Response<?> getAll() {
        return productCategoryService.getAll();
    }
}
