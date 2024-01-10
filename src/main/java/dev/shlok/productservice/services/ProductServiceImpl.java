package dev.shlok.productservice.services;

import dev.shlok.productservice.clients.fakestoreapi.FakeStoreProductDto;
import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
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
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }

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
            Product product = convertProductDtoToProduct(productDto);
            answer.add(product);
        }

        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
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
        if (productDto == null) {
            return Optional.empty();
        }

        return Optional.of(convertProductDtoToProduct(productDto));
    }

    @Override
    public Product addNewProduct(ProductDto productDto1) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto1,
                ProductDto.class);
        ProductDto productDto = responseEntity.getBody();
        Product product1 = convertProductDtoToProduct(productDto);

        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product deleteProduct(Long productId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());

    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        return null;
    }
}
