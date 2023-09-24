package dev.shlok.productservice.services;

import org.springframework.web.bind.annotation.GetMapping;

public interface CategoryService {
    @GetMapping("/categories")
    String getAllCategories();

    String getProductsInCategory();
}
