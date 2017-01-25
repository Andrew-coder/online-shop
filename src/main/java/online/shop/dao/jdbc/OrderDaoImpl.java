package online.shop.dao.jdbc;

import online.shop.dao.OrderDao;
import online.shop.dao.exception.DaoException;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.extractors.impl.OrderResultSetExtractor;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by andri on 1/5/2017.
 */

public class OrderDaoImpl implements OrderDao{
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final String GET_ALL_ORDERS = "select orderID, userID, orderDate, paid, totalPrice " +
            "userID, name, surname, email, password, birthDate, worker, role from (" +
            "torders join users using(userID))";
    private static final String FILTER_BY_ID = " where orderID=?;";
    private static final String FIND_GOODS_ITEMS = "select orderID, goodsID, title, price, ends, description, image, subcategoryID, subcategoryTitle, categoryID, categoryTitle from (" +
            "orderInfo left join (" +
            "goods join( subcategory_list join categories using(categoryID))using(subcategoryID)" +
            ") using (goodsID))";
    private static final String CREATE_ORDER = "insert into orders (`userID`, `orderDate`, `paid`) values (?,?,?);";
    private static final String CREATE_ORDER_ITEM = "insert into orderInfo (`orderID`, `googsID`, `amount`) values (?,?,?);";
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
            logger.error(ErrorMessages.ERROR_FIND_ONE_ORDER);
            throw new DaoException(ErrorMessages.ERROR_FIND_ONE_ORDER, ex);
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
            logger.error(ErrorMessages.ERROR_FIND_ALL_ORDERS);
            throw new DaoException(ErrorMessages.ERROR_FIND_ALL_ORDERS, ex);
        }
    }

    @Override
    public void create(Order order) {
        Objects.requireNonNull(order, "Error! Wrong order object...");
        try(PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)){
            statement.setInt(1, order.getUser().getId());
            statement.setDate(2, java.sql.Date.valueOf(order.getOrderDate().
                    toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            statement.setBoolean(3, order.isPaid());
            statement.executeUpdate();
            order.getGoodsItems().entrySet()
                        .stream()
                        .forEach(entry -> createOrderItem(order.getId(), entry.getKey().getId(), entry.getValue()));
        }
        catch (SQLException|DaoException ex){
            logger.error(ErrorMessages.ERROR_CREATE_ORDER);
            throw new DaoException(ErrorMessages.ERROR_CREATE_ORDER, ex);
        }
    }

    private void createOrderItem(int orderId, int goodsId, int amount){
        try(PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_ITEM)){
            statement.setInt(1, orderId);
            statement.setInt(2, goodsId);
            statement.setInt(3, amount);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            logger.error(ErrorMessages.ERROR_CREATE_ORDER_ITEM);
            throw new DaoException(ErrorMessages.ERROR_CREATE_ORDER_ITEM);
        }
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
            logger.error(ErrorMessages.ERROR_FIND_GOODS_ITEMS);
            throw new DaoException(ErrorMessages.ERROR_FIND_GOODS_ITEMS, ex);
        }
    }

    @Override
    public List<Order> findAllUserOrders(int userId) {
        return null;
    }

    @Override
    public List<Order> findAllUnpaidOrders() {
        return null;
    }
}
