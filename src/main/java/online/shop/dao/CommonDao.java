package online.shop.dao;

import online.shop.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/4/2017.
 */
public interface CommonDao<E extends BaseEntity> {
    Optional<E> findById(int id);
    List<E> findAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}
