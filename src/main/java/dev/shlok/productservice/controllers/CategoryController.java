package dev.shlok.productservice.controllers;

import dev.shlok.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/categories")
    public String getAllCategories(){
        return "Getting all categories";
    }


    public String getProductsInCategory(){
        return "Getting products in category";
    }
}
