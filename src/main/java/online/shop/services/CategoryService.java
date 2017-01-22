package online.shop.services;

import online.shop.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(int id);
    Optional<Category> findCategoryByTitle(String title);
    List<Category> findAll();
    void create(Category category);
    void update(Category category);
    void delete(int id);
}
