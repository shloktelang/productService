package dev.shlok.productservice.controllers;

import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

@GetMapping("/category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable String categoryName){
        return categoryService.getProductsInCategory(categoryName);
    }
}
