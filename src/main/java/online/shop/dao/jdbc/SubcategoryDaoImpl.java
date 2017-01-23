package online.shop.dao.jdbc;

import online.shop.dao.SubcategoryDao;
import online.shop.dao.exception.DaoException;
import online.shop.utils.extractors.impl.SubcategoryResultSetExtractor;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by andri on 1/5/2017.
 */
public class SubcategoryDaoImpl implements SubcategoryDao{
    private static final String GET_ALL_SUBCATEGORIES_IN_DETAILS = "select subcategoryID, subcategoryTitle, categoryID, categoryTitle from(" +
            "subcategory_list join categories using(categoryID)) ";
    private static final String FILTER_BY_CATEGORY = "where categoryID=?;";
    private static final String FILTER_BY_ID = "where subcategoryID=?;";
    private static final String FILTER_BY_TITLE = "where subcategoryTitle=?;";
    private static final String CREATE_SUBCATEGORY = "insert into subcategories (subcategoryTitle, categoryID) values (?, ?);";
    private static final String DELETE_SUBCATEGORY = "delete from subcategory_list ";
    private Connection connection;
    private SubcategoryResultSetExtractor extractor;

    public SubcategoryDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new SubcategoryResultSetExtractor();
    }

    @Override
    public Optional<Subcategory> findById(int id) {
        Optional<Subcategory> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES_IN_DETAILS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Subcategory subcategory = extractor.extract(set);
                result = Optional.of(subcategory);
            }
            return result;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategory by id", ex);
        }
    }

    @Override
    public List<Subcategory> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_SUBCATEGORIES_IN_DETAILS)){
            List<Subcategory> subcategories = new ArrayList<>();
            while(set.next()){
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
            statement.setInt(2, subcategory.getCategory().getId());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new subcategory!", ex);
        }
    }

    @Override
    public Optional<Subcategory> findSubcategoryByTitle(String title) {
        Optional<Subcategory> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES_IN_DETAILS+ FILTER_BY_TITLE)){
            statement.setString(1,title);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Subcategory subcategory = extractor.extract(set);
                result = Optional.of(subcategory);
            }
            return result;
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
        try(PreparedStatement statement = connection.prepareStatement(DELETE_SUBCATEGORY+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting subcategory!", ex);
        }
    }

    @Override
    public List<Subcategory> findSubcategoriesByCategoryId(int categoryId) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBCATEGORIES_IN_DETAILS+ FILTER_BY_CATEGORY)){
            statement.setInt(1, categoryId);
            List<Subcategory> subcategories = new ArrayList<>();
            ResultSet set = statement.executeQuery();
            while(set.next()){
                subcategories.add(extractor.extract(set));
            }
            return subcategories;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategories by category", ex);
        }
    }
}
