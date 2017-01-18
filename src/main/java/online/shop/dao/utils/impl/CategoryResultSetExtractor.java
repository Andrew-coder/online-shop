package online.shop.dao.utils.impl;

import online.shop.dao.utils.ResultSetExtractor;
import online.shop.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
