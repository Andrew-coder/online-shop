package online.shop.dao;

import online.shop.model.entity.BaseEntity;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface OrderDao<E extends BaseEntity> {
    E find(int id);
    List<E> findAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}
