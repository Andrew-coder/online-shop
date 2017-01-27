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
    private static final String GET_ALL_ORDERS = "select orderID, orderDate, paid, totalPrice, amount, userID, " +
            "    name, surname, email, password, birthDate, role, goodsID, " +
            "        title, price, ends, description, image, subcategoryID, " +
            "        subcategoryTitle, categoryID, categoryTitle from (" +
            "  orders join (" +
            "    users join roles on users.role=roles.roleID" +
            "    ) using(userID)" +
            "    join (" +
            "    orderinfo join (" +
            "      goods join (" +
            "        subcategory_list join categories using(categoryID)" +
            "            )using(subcategoryID)" +
            "        ) using(goodsID)" +
            "    ) using(orderID)" +
            ")  ";
    private static final String FILTER_BY_ID = " where orderID=?;";
    private static final String UPDATE_ORDER = "update orders set `paid`=? ";
    private static final String CREATE_ORDER = "insert into orders (`userID`, `orderDate`, `paid`, `totalPrice`) values (?,?,?,?);";
    private static final String CREATE_ORDER_ITEM = "insert into orderInfo (`orderID`, `goodsID`, `amount`) values (last_insert_id(),?,?);";
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
            statement.setLong(4, order.getTotalPrice());
            statement.executeUpdate();
            order.getGoodsItems().entrySet()
                        .stream()
                        .forEach(entry -> createOrderItem(entry.getKey().getId(), entry.getValue()));
        }
        catch (SQLException|DaoException ex){
            logger.error(ErrorMessages.ERROR_CREATE_ORDER);
            throw new DaoException(ErrorMessages.ERROR_CREATE_ORDER, ex);
        }
    }

    private void createOrderItem(int goodsId, int amount){
        try(PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_ITEM)){
            statement.setInt(1, goodsId);
            statement.setInt(2, amount);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            logger.error(ErrorMessages.ERROR_CREATE_ORDER_ITEM);
            throw new DaoException(ErrorMessages.ERROR_CREATE_ORDER_ITEM);
        }
    }

    @Override
    public void update(Order order) {
        checkEmptyOrder(order);
        checkUnsavedOrder(order);
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER + FILTER_BY_ID)){
            statement.setBoolean(1, order.isPaid());
            statement.setInt(2, order.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            logger.error(ErrorMessages.ERROR_UPDATE_ORDER);
            throw new DaoException(ErrorMessages.ERROR_UPDATE_ORDER, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> findAllUserOrders(int userId) {
        return null;
    }

    @Override
    public List<Order> findAllUnpaidOrders() {
        return null;
    }

    private void checkUnsavedOrder(Order order){
        if(order.getId()==0){
            throw new DaoException(ErrorMessages.UNSAVED_OBJECT);
        }
    }

    private void checkEmptyOrder(Order order){
        if(order == null){
            throw new DaoException(ErrorMessages.EMPTY_OBJECT);
        }
    }
}
