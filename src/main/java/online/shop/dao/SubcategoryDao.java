package online.shop.dao;

import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface SubcategoryDao extends CommonDao<Subcategory>{
    Subcategory findSubcategoryByTitle(String title);
    List<Subcategory> findSubcategoriesByCategory(Category category);
}
