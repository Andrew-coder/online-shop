package online.shop.dao;

import online.shop.model.entity.Order;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface OrderDao extends CommonDao<Order> {
    List<Order> findAllUserOrders(int userId);
    List<Order> findAllUnpaidOrders();
}
