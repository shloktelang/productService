package dev.shlok.productservice.services;

import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.dtos.RatingDto;
import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;        //this will allow us to call 3rd party APIs
    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for (ProductDto productDto: l.getBody()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }

        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // following method is used to GET, but it will be returning a JSON object,
        // so in the second parameter we are giving it a class and the rest template will try to convert the json
        // into the object of the given class
        // the data members of the class should have the same name as the attributes of JSON returned
        // third parameter is the {id} to be given
        ResponseEntity<ProductDto> responseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class,
                productId);

        ProductDto productDto = responseEntity.getBody();
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRate(productDto.getRating().getRate());
        ratingDto.setCount(productDto.getRating().getCount());
        product.setRating(ratingDto);

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto1) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto1,
                ProductDto.class);
        ProductDto productDto = responseEntity.getBody();
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

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
