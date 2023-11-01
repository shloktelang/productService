package dev.shlok.productservice.repositories;

import dev.shlok.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfProductRepository extends JpaRepository<Product,Long> {
}
