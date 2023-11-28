package dev.shlok.productservice.services;

import dev.shlok.productservice.models.Category;
import dev.shlok.productservice.models.Product;
import dev.shlok.productservice.repositories.SelfCategoryRepository;
import dev.shlok.productservice.repositories.SelfProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service(value = "selfCategoryService")
@Primary
public class SelfCategoryService implements CategoryService {
    SelfCategoryRepository selfCategoryRepository;
    SelfProductRepository selfProductRepository;
    public SelfCategoryService(SelfCategoryRepository selfCategoryRepository, SelfProductRepository selfProductRepository){
        this.selfCategoryRepository = selfCategoryRepository;
        this.selfProductRepository = selfProductRepository;
    }


   public List<String> getAllCategories(){
        List<Category> cat = selfCategoryRepository.findAll();
        HashSet<String> hs = new HashSet<>();
        for(Category category : cat){
            hs.add(category.getName());
        }
        List<String> ans = new ArrayList<>();
        ans.addAll(hs);
        return ans;
    }

    public List<Product> getProductsInCategory(String categoryName){
        List<Product> product = selfProductRepository.findAll();
        List<Product> ans = new ArrayList<>();
        for(Product p : product){
            if(p.getCategory().getName().equals(categoryName)){
                ans.add(p);
            }
        }
        return ans;
    }
}
