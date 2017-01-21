package online.shop.dao.jdbc;

import online.shop.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by andri on 1/5/2017.
 */
public class JdbcDaoFactory extends DaoFactory {
    private DataSource dataSource = DataSourceProvider.setupDataSource();

    public JdbcDaoFactory() {
    }

    @Override
    public ConnectionWrapper getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new ConnectionWrapperImpl(connection);
    }

    @Override
    public CategoryDao getCategoryDao(ConnectionWrapper wrapper) {
        return new CategoryDaoImpl(extractSqlConnection(wrapper));
    }

    @Override
    public SubcategoryDao getSubcategoryDao(ConnectionWrapper wrapper) {
        return new SubcategoryDaoImpl(extractSqlConnection(wrapper));
    }

    @Override
    public UserDao getUserDao(ConnectionWrapper wrapper) {
        return new UserDaoImpl(extractSqlConnection(wrapper));
    }

    @Override
    public OrderDao getOrderDao(ConnectionWrapper wrapper) {
        return new OrderDaoImpl(extractSqlConnection(wrapper));
    }

    @Override
    public GoodsDao getGoodsDao(ConnectionWrapper wrapper) {
        return new GoodsDaoImpl(extractSqlConnection(wrapper));
    }

    public Connection extractSqlConnection(ConnectionWrapper wrapper) {
        ConnectionWrapperImpl connectionWrapper = (ConnectionWrapperImpl) wrapper;
        return connectionWrapper.getConnection();
    }
}
