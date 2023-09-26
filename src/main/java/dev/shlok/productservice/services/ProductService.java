package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();


    Product getSingleProduct(Long productId);


    Product addNewProduct(ProductDto productDto);


    Product updateProduct(Long productId, Product product);


    boolean deleteProduct(@PathVariable("productId") Long productId);
}
