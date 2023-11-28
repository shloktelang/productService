package dev.shlok.productservice.controllers;

import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.services.CategoryService;
import dev.shlok.productservice.services.SelfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private final SelfCategoryService selfCategoryService;

    public CategoryController(SelfCategoryService selfCategoryService){
        this.selfCategoryService=selfCategoryService;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return selfCategoryService.getAllCategories();
    }

@GetMapping("/category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable String categoryName){
        return selfCategoryService.getProductsInCategory(categoryName);
    }
}
