package online.shop.dao.jdbc;

import online.shop.dao.CategoryDao;
import online.shop.dao.exception.DaoException;
import online.shop.dao.utils.ResultSetExtractor;
import online.shop.dao.utils.impl.CategoryResultSetExtractor;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andri on 1/5/2017.
 */
public class CategoryDaoImpl implements CategoryDao {
    private static final String GET_ALL_CATEGORIES = "select categoryID, categoryTitle from categories ";
    private static final String FILTER_BY_ID = "where categoryID=?;";
    private static final String FILTER_BY_TITLE = "where categoryTitle=?;";
    private static final String CREATE_CATEGORY = "insert into categories (categoryTitle) values (?);";
    private static final String DELETE_CATEGORY = "delete from categories ";
    private Connection connection;
    private CategoryResultSetExtractor extractor;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new CategoryResultSetExtractor();
    }

    @Override
    public Category findCategoryByTitle(String title) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIES+ FILTER_BY_TITLE)){
            statement.setString(1,title);
            Category category = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                category = extractor.extract(set);
            }
            return category;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving category by title", ex);
        }
    }

    @Override
    public Category findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIES+ FILTER_BY_ID)){
            statement.setInt(1,id);
            Category category = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                category = extractor.extract(set);
            }
            return category;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving category by id", ex);
        }
    }

    @Override
    public List<Category> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_CATEGORIES)){
            List<Category> categories = new ArrayList<>();
            if(set.next()){
                categories.add(extractor.extract(set));
            }
            return categories;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all categories", ex);
        }
    }

    @Override
    public void create(Category category) {
        Objects.requireNonNull(category, "Error! Wrong category object...");
        try(PreparedStatement statement = connection.prepareStatement(CREATE_CATEGORY)){
            statement.setString(1, category.getTitle());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new category!", ex);
        }
    }

    @Override
    public void update(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        Category category = findById(id);
        Objects.requireNonNull(category, "Category with such id wasn't found");
        try(PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting category!", ex);
        }
    }
}
