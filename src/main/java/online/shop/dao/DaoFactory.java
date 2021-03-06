package online.shop.dao;

import online.shop.model.entity.Subcategory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by andri on 1/5/2017.
 */
public abstract class DaoFactory {

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static class InstanceHolder{
        private static final DaoFactory instance = setUpDaoFactory();
    }

    public static DaoFactory getInstance(){
        return InstanceHolder.instance;
    }

    public static DaoFactory setUpDaoFactory(){
        DaoFactory factory;
        try {
            InputStream inputStream =
                    DaoFactory.class.getResourceAsStream(DB_FILE);
            Properties dbProps = new Properties();
            dbProps.load(inputStream);
            String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
            factory = (DaoFactory) Class.forName(factoryClass).newInstance();
        } catch (IOException | IllegalAccessException|
                InstantiationException |ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
        return factory;
    }

    public abstract ConnectionWrapper getConnection();
    public abstract CategoryDao getCategoryDao(ConnectionWrapper wrapper);
    public abstract SubcategoryDao getSubcategoryDao(ConnectionWrapper wrapper);
    public abstract UserDao getUserDao(ConnectionWrapper wrapper);
    public abstract OrderDao getOrderDao(ConnectionWrapper wrapper);
    public abstract GoodsDao getGoodsDao(ConnectionWrapper wrapper);
}
