package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();


    Optional<Product> getSingleProduct(Long productId);


    Product addNewProduct(ProductDto productDto);


    Product updateProduct(Long productId, Product product);


    Product deleteProduct(Long productId);
}
