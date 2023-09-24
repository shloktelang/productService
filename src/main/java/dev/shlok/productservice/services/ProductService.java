package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductService {
    @GetMapping()
    String getAllProducts();

    @GetMapping("/{productId}")
    String getSingleProduct(@PathVariable("productId") Long productId);

    @PostMapping()
    String addNewProduct(@RequestBody ProductDto productDto);

    @PutMapping("/{productId}")
    String updateProduct(@PathVariable("productId") Long productId);

    @DeleteMapping("/{productId}")
    String deleteProduct(@PathVariable("productId") Long productId);
}
