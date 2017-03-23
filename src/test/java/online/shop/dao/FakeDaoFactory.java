package online.shop.dao;

/**
 * Created by andri on 1/30/2017.
 */
public class FakeDaoFactory extends DaoFactory {
    @Override
    public ConnectionWrapper getConnection() {
        return null;
    }

    @Override
    public CategoryDao getCategoryDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public SubcategoryDao getSubcategoryDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public UserDao getUserDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public OrderDao getOrderDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public GoodsDao getGoodsDao(ConnectionWrapper wrapper) {
        return null;
    }
}
