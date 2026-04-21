package uz.brb.java26.service;

import uz.brb.java26.dto.request.ProductCategoryRequest;
import uz.brb.java26.dto.response.Response;

public interface ProductCategoryService {
    Response<?> create(ProductCategoryRequest request);

    Response<?> getAll();
}
