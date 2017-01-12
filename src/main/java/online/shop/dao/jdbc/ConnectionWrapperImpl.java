package online.shop.dao.jdbc;

import online.shop.dao.ConnectionWrapper;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by andri on 1/11/2017.
 */
public class ConnectionWrapperImpl implements ConnectionWrapper {
    private Connection connection;

    public ConnectionWrapperImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void beginTransaction() {
        try{
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
