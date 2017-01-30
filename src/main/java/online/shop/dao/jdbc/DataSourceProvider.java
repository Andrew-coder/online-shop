package online.shop.dao.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

/**
 * Created by andri on 1/5/2017.
 */
public class DataSourceProvider {
    private static final String DB_PROPERTIES = "db";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_DRIVER = "jdbc.driver";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String MIN_POOL_SIZE = "min.pool.size";
    private static final String MAX_POOL_SIZE = "max.pool.size";
    private static final String ACQUIRE_INCREMENT = "acquire.increment";

    private static class InstanceHolder{
        private static final DataSource instance = setupDataSource();
    }

    public static DataSource getInstance(){
        return InstanceHolder.instance;
    }

    public static DataSource setupDataSource() {
        ResourceBundle dbProperties = ResourceBundle.getBundle(DB_PROPERTIES);
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(dbProperties.getString(JDBC_DRIVER));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(dbProperties.getString(JDBC_URL));
        cpds.setUser(dbProperties.getString(USER));
        cpds.setPassword(dbProperties.getString(PASSWORD));
        cpds.setMinPoolSize(Integer.parseInt(dbProperties.getString(MIN_POOL_SIZE)));
        cpds.setAcquireIncrement(Integer.parseInt(dbProperties.getString(ACQUIRE_INCREMENT)));
        cpds.setMaxPoolSize(Integer.parseInt(dbProperties.getString(MAX_POOL_SIZE)));
        return cpds;
    }
}
