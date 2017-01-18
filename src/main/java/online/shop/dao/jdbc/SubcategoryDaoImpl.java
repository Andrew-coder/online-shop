package online.shop.dao.jdbc;

import online.shop.dao.SubcategoryDao;
import online.shop.dao.exception.DaoException;
import online.shop.dao.utils.impl.SubcategoryResultSetExtractor;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andri on 1/5/2017.
 */
public class SubcategoryDaoImpl implements SubcategoryDao{
    private static final String GET_ALL_SUBCATEGORIES = "select subcategoryID, subcategoryTitle, categoryID from subcategory_list ";
    private static final String FILTER_BY_CATEGORY = "where categoryID=?;";
    private static final String FILTER_BY_ID = "where subcategoryID=?;";
    private static final String FILTER_BY_TITLE = "where subcategoryTitle=?;";
    private static final String CREATE_SUBCATEGORY = "insert into subcategories (subcategoryTitle) values (?);";
    private static final String DELETE_SUBCATEGORY = "delete from subcategory_list ";
    private Connection connection;
    private SubcategoryResultSetExtractor extractor;

    public SubcategoryDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new SubcategoryResultSetExtractor();
    }

    @Override
    public Subcategory findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES+ FILTER_BY_ID)){
            statement.setInt(1,id);
            Subcategory subcategory = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                subcategory = extractor.extract(set);
            }
            return subcategory;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategory by id", ex);
        }
    }

    @Override
    public List<Subcategory> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_SUBCATEGORIES)){
            List<Subcategory> subcategories = new ArrayList<>();
            if(set.next()){
                subcategories.add(extractor.extract(set));
            }
            return subcategories;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all subcategories", ex);
        }
    }

    @Override
    public void create(Subcategory subcategory) {
        Objects.requireNonNull(subcategory, "Error! Wrong subcategory object...");
        try(PreparedStatement statement = connection.prepareStatement(CREATE_SUBCATEGORY)){
            statement.setString(1, subcategory.getTitle());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new subcategory!", ex);
        }
    }

    @Override
    public Subcategory findSubcategoryByTitle(String title) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES+ FILTER_BY_TITLE)){
            statement.setString(1,title);
            Subcategory subcategory = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                subcategory = extractor.extract(set);
            }
            return subcategory;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategory by title", ex);
        }
    }

    @Override
    public void update(Subcategory subcategory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        Subcategory subcategory = findById(id);
        Objects.requireNonNull(subcategory, "Subcategory with such id wasn't found");
        try(PreparedStatement statement = connection.prepareStatement(DELETE_SUBCATEGORY+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting subcategory!", ex);
        }
    }

    @Override
    public List<Subcategory> findSubcategoriesByCategory(Category category) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES+ FILTER_BY_CATEGORY)){
            statement.setInt(1, category.getId());
            List<Subcategory> subcategories = new ArrayList<>();
            ResultSet set = statement.executeQuery();
            if(set.next()){
                subcategories.add(extractor.extract(set));
            }
            return subcategories;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategories by category", ex);
        }
    }
}
