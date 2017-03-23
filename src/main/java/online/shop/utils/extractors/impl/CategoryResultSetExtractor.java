package online.shop.utils.extractors.impl;

import online.shop.utils.extractors.ResultSetExtractor;
import online.shop.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andri on 1/17/2017.
 */
public class CategoryResultSetExtractor implements ResultSetExtractor<Category> {
    @Override
    public Category extract(ResultSet set) throws SQLException {
        Category category = new Category();
        category.setId(set.getInt("categoryID"));
        category.setTitle(set.getString("categoryTitle"));
        return category;
    }
}
