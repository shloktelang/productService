package dev.shlok.productservice.controllers;

import dev.shlok.productservice.dtos.GetSingleProductResponseDto;
import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // headers are used to add some extra info (metadata to the response)
        // check example in the recording for login authentication
        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product){
        ResponseEntity<Product> response = new ResponseEntity<>
                (productService.addNewProduct(product),
                        HttpStatus.OK);
        return response;
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId){
        return null;
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Long productId){
        return false;
    }
}