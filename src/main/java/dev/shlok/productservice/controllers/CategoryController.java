package dev.shlok.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/categories")
    public String getAllCategories(){
        return "Getting all categories";
    }

    public String getProductsInCategory(){
        return "Getting products in category";
    }
}
