package online.shop.dao.jdbc;

import online.shop.dao.OrderDao;
import online.shop.dao.exception.DaoException;
import online.shop.model.entity.Category;
import online.shop.model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andri on 1/5/2017.
 */
public class OrderDaoImpl implements OrderDao{
    private static final String GET_ALL_ORDERS = "";
    private static final String FILTER_BY_ID = "";
    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            List<Order> orders = parseResultSet(statement.executeQuery());
            return orders.get(0);
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving order by id", ex);
        }
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void create(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    private List<Order> parseResultSet(ResultSet set) throws SQLException{
        List<Order> orders = new ArrayList<>();

        return orders;
    }
}
