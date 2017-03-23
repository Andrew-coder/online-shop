package online.shop.dao;

import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/4/2017.
 */
public interface SubcategoryDao extends CommonDao<Subcategory>{
    Optional<Subcategory> findSubcategoryByTitle(String title);
    List<Subcategory> findSubcategoriesByCategoryId(int categoryId);
}
