package dev.shlok.productservice.controllers;

import dev.shlok.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryService {
    @Override
    @GetMapping("/categories")
    public String getAllCategories(){
        return "Getting all categories";
    }

    @Override
    public String getProductsInCategory(){
        return "Getting products in category";
    }
}
