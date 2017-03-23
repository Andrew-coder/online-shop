package online.shop.dao.jdbc;

import online.shop.dao.GoodsDao;
import online.shop.dao.exception.DaoException;
import online.shop.utils.extractors.impl.GoodsResultSetExtractor;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by andri on 1/5/2017.
 */
public class GoodsDaoImpl implements GoodsDao {
    private static final Logger logger = Logger.getLogger(GoodsDaoImpl.class);
    private static final String GET_ALL_GOODS = "select goodsID, title, price, image, availability, description,  subcategoryID, subcategoryTitle, categoryID, categoryTitle from(" +
            "goods join (subcategory_list join categories using(categoryID)" +
            ")using(subcategoryID)) ";
    private static final String FILTER_BY_ID = " where goodsID=?;";
    private static final String FILTER_BY_SUBCATEGORY = " where subcategoryID=?;";
    private static final String FILTER_BY_PRICE_RANGE = " where price >=? and price <=?;";
    private static final String CREATE_GOODS = "insert into goods (`title`, `price`, `availability`, `description`, `subcategoryID`) values (?,?,?,?,?);";
    private static final String DELETE_GOODS = "delete from goods ";
    private static final String UPDATE_GOODS = "update `online-shop`.`goods` set `title`=?, `price`=?, `availability`=?, `description`=?, `subcategoryID`=? ";
    private Connection connection;
    private GoodsResultSetExtractor extractor;

    public GoodsDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new GoodsResultSetExtractor();
    }

    @Override
    public Optional<Goods> findById(int id) {
        Optional<Goods> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Goods goods = extractor.extract(set);
                result = Optional.of(goods);
            }
            return result;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving goods by id", ex);
        }
    }

    @Override
    public List<Goods> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_GOODS)){
            List<Goods> goods = new ArrayList<>();
            while(set.next()){
                goods.add(extractor.extract(set));
            }
            return goods;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all goods", ex);
        }
    }

    @Override
    public List<Goods> findGoodsByPriceRange(double minPrice, double maxPrice) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_PRICE_RANGE)){
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet set = statement.executeQuery();
            List<Goods> goods = new ArrayList<>();
            while(set.next()){
                goods.add(extractor.extract(set));
            }
            return goods;
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
            statement.setLong(2,goods.getPrice());
            statement.setString(3, goods.getGoodsStatus().getGoodsStatus());
            statement.setString(4,goods.getDescription());
            statement.setInt(5, goods.getSubcategory().getId());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new goods!", ex);
        }
    }

    @Override
    public void update(Goods goods) {
        Objects.requireNonNull(goods, "Error! Wrong goods object...");
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_GOODS + FILTER_BY_ID)){
            statement.setString(1,goods.getTitle());
            statement.setLong(2,goods.getPrice());
            statement.setString(3, goods.getGoodsStatus().getGoodsStatus());
            statement.setString(4,goods.getDescription());
            statement.setInt(5, goods.getSubcategory().getId());
            statement.setInt(6, goods.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new DaoException("Error occured when updating goods!", ex);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_GOODS+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting goods!", ex);
        }
    }

    @Override
    public List<Goods> findGoodsBySubcategoryId(int subcategoryId) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_GOODS+ FILTER_BY_SUBCATEGORY)){
            statement.setInt(1,subcategoryId);
            ResultSet set = statement.executeQuery();
            List<Goods> goods = new ArrayList<>();
            while(set.next()){
                goods.add(extractor.extract(set));
            }
            return goods;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving subcategories by category", ex);
        }
    }
}
