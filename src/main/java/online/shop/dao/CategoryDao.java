package online.shop.dao;

import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface CategoryDao extends CommonDao<Category> {
    Category findCategoryByTitle(String title);
}
