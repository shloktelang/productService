package dev.shlok.productservice.repositories;

import dev.shlok.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);
}
