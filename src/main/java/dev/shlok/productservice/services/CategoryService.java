package dev.shlok.productservice.services;

import dev.shlok.productservice.models.Product;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();

    List<Product> getProductsInCategory(String categoryName);
}
