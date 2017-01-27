package online.shop.services;

import online.shop.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/23/2017.
 */
public interface OrderService {
    Optional<Order> findById(int id);
    List<Order> findAll();
    void create(Order order);
    void update(Order order);
    void updateOrderStatus(int orderId, boolean paid);
    List<Order> findAllUserOrders(int userId);
    List<Order> findAllUnpaidOrders();
}
