package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.OrderDao;
import online.shop.model.entity.Order;
import online.shop.services.OrderService;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/23/2017.
 */
public class OrderServiceImpl implements OrderService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderServiceImpl(){}

    private static OrderService instance;

    public static synchronized  OrderService getInstance(){
        if(instance==null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Order> findById(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            return orderDao.findById(id);
        }
    }

    @Override
    public List<Order> findAll() {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ){
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            return orderDao.findAll();
        }
    }

    @Override
    public void create(Order order) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ) {
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            order.setTotalPrice(calculateTotalPrice(order));
            wrapper.beginTransaction();
            orderDao.create(order);
            wrapper.commitTransaction();
        }
    }

    @Override
    public void update(Order order) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ) {
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            wrapper.beginTransaction();
            orderDao.update(order);
            wrapper.commitTransaction();
        }
    }

    @Override
    public List<Order> findAllUserOrders(int userId) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ) {
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            return orderDao.findAllUserOrders(userId);
        }
    }

    @Override
    public List<Order> findAllUnpaidOrders() {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ) {
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            return orderDao.findAllUnpaidOrders();
        }
    }

    private long calculateTotalPrice(Order order){
        return order.getGoodsItems().entrySet()
                                .stream()
                                .mapToLong(entry -> (long)entry.getKey().getPrice()*entry.getValue())
                                .sum();
    }
}

