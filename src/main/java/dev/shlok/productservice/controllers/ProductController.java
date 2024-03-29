package dev.shlok.productservice.controllers;

import dev.shlok.productservice.clients.authenticationclient.AuthenticationClient;
import dev.shlok.productservice.clients.authenticationclient.dtos.Role;
import dev.shlok.productservice.clients.authenticationclient.dtos.SessionStatus;
import dev.shlok.productservice.clients.authenticationclient.dtos.ValidatetokenResponseDto;
import dev.shlok.productservice.dtos.ErrorResponseDto;
import dev.shlok.productservice.dtos.GetProductsRequestDto;
import dev.shlok.productservice.dtos.GetSingleProductResponseDto;
import dev.shlok.productservice.dtos.ProductDto;
import dev.shlok.productservice.exceptions.NotFoundException;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.repositories.SelfProductRepository;
import dev.shlok.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
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
    private SelfProductRepository productRepository;

    private AuthenticationClient authenticationClient;

    public ProductController( ProductService productService, SelfProductRepository productRepository, AuthenticationClient authenticationClient) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.authenticationClient = authenticationClient;
    }

    public ResponseEntity<Page<Product>> getProducts(GetProductsRequestDto request){
        Page<Product> products = productService.getProducts(
                request.getNumberOfResults(), request.getOffset());
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //Make only admins be able to access getallproducts, auth service
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                        @Nullable @RequestHeader("USER_ID") Long userId) {
//        // check if token exists
//        if (token == null || userId == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        ValidatetokenResponseDto response = authenticationClient.validate(token, userId);
//
//        // check if token is valid
//        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        // validate token
//        // RestTemplate rt = new RestTRemplate();
//        //  rt.get("localhost:9090/auth/validate?)
//
//        // check if user has permissions
//        boolean isUserAdmin = false;
//        for (Role role: response.getUserDto().getRoles()) {
//            if (role.getName().equals("ADMIN")) {
//                isUserAdmin = true;
//            }
//        }
//
//        if (!isUserAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
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
                HttpStatus.OK
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