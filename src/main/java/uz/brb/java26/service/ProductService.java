package uz.brb.java26.service;

import uz.brb.java26.dto.request.ProductRequest;
import uz.brb.java26.dto.response.Response;

public interface ProductService {
    Response<?> create(ProductRequest request);

    Response<?> lookup(String code);

    Response<?> getAll();
}
