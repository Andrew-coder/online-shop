package online.shop.dao.jdbc;

import online.shop.dao.ConnectionWrapper;

import java.sql.Connection;

/**
 * Created by andri on 1/11/2017.
 */
public class ConnectionWrapperImpl implements ConnectionWrapper {
    private Connection connection;

    public ConnectionWrapperImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
