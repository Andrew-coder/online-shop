package online.shop.dao;

/**
 * Created by andri on 1/11/2017.
 */
public interface ConnectionWrapper extends AutoCloseable {
    void beginTransaction();
    void commitTransaction();
    void rollbackTransaction();
}
