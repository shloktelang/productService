package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.repositories.SelfProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
@Primary
public class SelfProductService implements ProductService{
    SelfProductRepository selfProductRepository;
    public SelfProductService(SelfProductRepository selfProductRepository){
        this.selfProductRepository = selfProductRepository;
    }
    public List<Product> getAllProducts(){
        return selfProductRepository.findAll();
    }

    public Optional<Product> getSingleProduct(Long productId){
        return selfProductRepository.findById(productId);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    public Product addNewProduct(Product product){
        return selfProductRepository.save(product);
    }

    public Product updateProduct(Product product, Long productId){
        Optional<Product> optionalProduct = selfProductRepository.findById(productId);
        Product target = optionalProduct.get();
        if(product.getTitle()!=null){
            target.setTitle(product.getTitle());
        }
        if(product.getPrice()!=null){
            target.setPrice(product.getPrice());
        }
        if(product.getDescription()!=null){
            target.setDescription(product.getDescription());
        }
        if(product.getCategory()!=null){
            target.setCategory(product.getCategory());
        }
        if(product.getImageUrl()!=null){
            target.setImageUrl(product.getImageUrl());
        }
        return target;
    }

    public Product deleteProduct(Long productId){
        return null;
    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        Page<Product> products = selfProductRepository.findAll(
                PageRequest.of((offset/numberOfProducts),numberOfProducts)
        );
        return products;
    }
}
