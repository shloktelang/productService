package dev.shlok.productservice.controllers;

import dev.shlok.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping()
    public String getAllProducts(){
        return "Getting all products";
    }
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "Getting Single Product with id " +productId;
    }
    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding new product " +productDto;
    }
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "Updating product with id "+productId;
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting product with id "+productId;
    }
}
