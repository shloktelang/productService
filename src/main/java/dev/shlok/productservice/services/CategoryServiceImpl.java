package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private Product convertProductDtoToProduct(ProductDto productDto){
        Product product1 = new Product();
        product1.setId(productDto.getId());
        product1.setTitle(productDto.getTitle());
        product1.setPrice(productDto.getPrice());
        product1.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto.getImage());

        return product1;
    }
    private final RestTemplateBuilder restTemplateBuilder;

    public CategoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        List<String> answer = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();

        for (ProductDto productDto: l.getBody()) {
            String category = productDto.getCategory();
            hs.add(category);
        }
        answer.addAll(hs);
        return answer;
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        List<Product> answer = new ArrayList<>();
        for (ProductDto productDto: l.getBody()) {
            String category = productDto.getCategory();
            Product product = convertProductDtoToProduct(productDto);
            if(category.equals(categoryName)){
                answer.add(product);
            }
        }
        return answer;
    }
}
