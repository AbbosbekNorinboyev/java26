package uz.brb.java26.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.brb.java26.dto.request.ProductRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    Response<?> create(@RequestBody ProductRequest request) {
        return productService.create(request);
    }

    @GetMapping("/lookup")
    public Response<?> lookup(@RequestParam String code) {
        return productService.lookup(code);
    }

    @GetMapping()
    public Response<?> getAll() {
        return productService.getAll();
    }
}
