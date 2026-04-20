package uz.brb.java26.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brb.java26.dto.request.ProductCategoryRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.service.ProductCategoryService;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService service;

    @PostMapping
    Response<?> create(@RequestBody ProductCategoryRequest request) {
        return service.create(request);
    }
}
