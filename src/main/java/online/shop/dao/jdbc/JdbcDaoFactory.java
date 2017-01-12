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

    private static JdbcDaoFactory instance;

    private JdbcDaoFactory() {

    }

    public static DaoFactory getInstance(){
        if(instance==null){
            instance = new JdbcDaoFactory();
        }
        return instance;
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
    public CategoryDao getCategoryDao() {
        return null;
    }

    @Override
    public SubcategoryDao getSubcategoryDao() {
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }

    @Override
    public OrderDao getOrderDao() {
        return null;
    }

    @Override
    public GoodsDao getGoodsDao() {
        return null;
    }
}
