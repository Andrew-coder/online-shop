package online.shop.dao.jdbc;

import online.shop.dao.OrderDao;
import online.shop.dao.exception.DaoException;
import online.shop.dao.utils.impl.OrderResultSetExtractor;
import online.shop.model.entity.Category;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andri on 1/5/2017.
 */

public class OrderDaoImpl implements OrderDao{
    private static final String GET_ALL_ORDERS = "select orderID, userID, orderDate, " +
            "userID, name, surname, email, password, birthDate, worker, role from (" +
            "torders join users using(userID)" +
            ")";
    private static final String FILTER_BY_ID = " where orderID=?;";
    private static final String FIND_GOODS_ITEMS = "select orderID, goodsID, title, price, ends, description, image, subcategoryID, subcategoryTitle from (" +
            "orderInfo left join (" +
            "goods join subcategory_list using(subcategoryID)" +
            "    ) using (goodsID)" +
            ")";
    private Connection connection;
    private OrderResultSetExtractor extractor;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new OrderResultSetExtractor();
    }

    @Override
    public Optional<Order> findById(int id) {
        Optional<Order> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Order order = extractor.extract(set);
                order.setGoodsItems(findGoodsItems(order.getId()));
                result = Optional.of(order);
            }
            return result;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving order by id", ex);
        }
    }

    @Override
    public List<Order> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_ORDERS)){
            List<Order> orders = new ArrayList<>();
            while(set.next()){
                Order order = extractor.extract(set);
                order.setGoodsItems(findGoodsItems(order.getId()));
                orders.add(order);
            }
            return orders;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all orders", ex);
        }
    }

    @Override
    public void create(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    private Map<Goods,Integer> findGoodsItems(int orderID){
        try(PreparedStatement statement = connection.prepareStatement(FIND_GOODS_ITEMS+FILTER_BY_ID)){
            statement.setInt(1,orderID);
            Map<Goods, Integer> goodsItems = null;
            ResultSet set = statement.executeQuery();
            goodsItems=extractor.extractGoodsItems(set);
            return goodsItems;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving goods items in order by order id", ex);
        }
    }
}
