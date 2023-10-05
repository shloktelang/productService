package dev.shlok.productservice.controllers;

import dev.shlok.productservice.dtos.ErrorResponseDto;
import dev.shlok.productservice.dtos.GetSingleProductResponseDto;
import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.exceptions.NotFoundException;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // headers are used to add some extra info (metadata to the response)
        // check example in the recording for login authentication
        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()){
            throw new NotFoundException("No product with product id "+productId);
        }
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

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.deleteProduct(productId),
                HttpStatus.OK
        );
        return response;
    }

}