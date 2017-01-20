package online.shop.services;

import online.shop.model.entity.Category;

import java.util.List;

/**
 * Created by andri on 1/19/2017.
 */
public interface CategoryService {
    Category findById(int id);
    Category findCategoryByTitle(String title);
    List<Category> findAll();
    void create(Category category);
    void update(Category category);
    void delete(int id);
}
