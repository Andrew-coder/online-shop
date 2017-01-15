package online.shop.dao.jdbc;

import online.shop.dao.GoodsDao;
import online.shop.dao.exception.DaoException;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andri on 1/5/2017.
 */
public class GoodsDaoImpl implements GoodsDao {
    private static final String GET_ALL_GOODS = "select goodsID, title, price, ends, description, subcategoryID from goods ";
    private static final String FILTER_BY_ID = " where goodsID=?;";
    private static final String FILTER_BY_SUBCATEGORY = " where subcategoryID=?;";
    private static final String FILTER_BY_PRICE_RANGE = " where price >=? and price <=?;";
    private static final String CREATE_GOODS = "insert into goods (`title`, `price`, `description`, `subcategoryID`) VALUES (?,?,?,?);";
    private static final String DELETE_GOODS = "delete from goods ";
    private Connection connection;

    public GoodsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Goods findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            List<Goods> goods = parseResultSet(statement.executeQuery());
            return goods.get(0);
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving goods by id", ex);
        }
    }

    @Override
    public List<Goods> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_GOODS)){
            return parseResultSet(resultSet);
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all goods", ex);
        }
    }

    @Override
    public Goods findGoodsByPriceRange(double minPrice, double maxPrice) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_PRICE_RANGE)){
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            List<Goods> goods = parseResultSet(statement.executeQuery());
            return goods.get(0);
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving goods by price range", ex);
        }
    }

    @Override
    public void create(Goods goods) {
        Objects.requireNonNull(goods, "Error! Wrong goods object...");
        try(PreparedStatement statement = connection.prepareStatement(CREATE_GOODS)){
            statement.setString(1,goods.getTitle());
            statement.setDouble(2,goods.getPrice());
            statement.setString(3,goods.getDescription());
            statement.setInt(4, goods.getSubcategory().getId());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new goods!", ex);
        }
    }

    @Override
    public void update(Goods goods) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        Goods goods = findById(id);
        Objects.requireNonNull(goods, "Goods with such id wasn't found");
        try(PreparedStatement statement = connection.prepareStatement(DELETE_GOODS+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting goods!", ex);
        }
    }

    @Override
    public List<Goods> findGoodsBySubcategory(Subcategory subcategory) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_SUBCATEGORY)){
            statement.setInt(1,subcategory.getId());
            List<Goods> goods = parseResultSet(statement.executeQuery());
            return goods;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategories by category", ex);
        }
    }

    public List<Goods> parseResultSet(ResultSet set) throws SQLException{
        List<Goods> goods = new ArrayList<>();
        while(set.next()){
            Goods g = new Goods.Builder()
                        .setTitle(set.getString("title"))
                        .setPrice(set.getDouble("price"))
                        .setDescription(set.getString("description"))
                        .setEnds(set.getBoolean("ends"))
                        .setSubcategory(new Subcategory(set.getInt("subcategoryID")))
                        .build();
            goods.add(g);
        }
        return goods;
    }
}
