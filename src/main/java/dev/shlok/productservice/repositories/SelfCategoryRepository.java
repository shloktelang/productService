package dev.shlok.productservice.repositories;

import dev.shlok.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfCategoryRepository extends JpaRepository<Category,Long> {
}
