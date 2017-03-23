package online.shop.services;

import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/22/2017.
 */
public interface SubcategoryService {
    Optional<Subcategory> findById(int id);
    Optional<Subcategory> findSubcategoryByTitle(String title);
    List<Subcategory> findSubcategoriesByCategoryId(int categoryId);
    List<Subcategory> findAll();
    void create(Subcategory subcategory);
    void update(Subcategory subcategory);
    void delete(Subcategory subcategory);
}
