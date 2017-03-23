package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.OrderDao;
import online.shop.dao.exception.DaoException;
import online.shop.model.entity.Order;
import online.shop.model.entity.User;
import online.shop.services.OrderService;
import online.shop.services.UserService;
import online.shop.services.exception.ServiceException;
import online.shop.utils.constants.ErrorMessages;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/23/2017.
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    public OrderServiceImpl(UserService userService) {
        this.userService = userService;
    }

    OrderServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final OrderService instance = new OrderServiceImpl(DaoFactory.getInstance());
    }

    public static OrderService getInstance(){
        return InstanceHolder.instance;
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
        checkEmptyOrder(Optional.ofNullable(order));
        checkIfIsUserInBlacklist(order.getUser());
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
        checkEmptyOrder(Optional.of(order));
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ) {
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            wrapper.beginTransaction();
            orderDao.update(order);
            wrapper.commitTransaction();
        }
    }

    @Override
    public void updateOrderStatus(int orderId, boolean paid) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            OrderDao orderDao = daoFactory.getOrderDao(wrapper);
            Optional<Order> order = orderDao.findById(orderId);
            checkEmptyOrder(order);
            order.get().setPaid(paid);
            orderDao.update(order.get());
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

    private void checkEmptyOrder(Optional<Order> order){
        if(!order.isPresent()){
            logger.error(ErrorMessages.EMPTY_OBJECT);
            throw new ServiceException(ErrorMessages.EMPTY_OBJECT);
        }
    }

    private void checkIfIsUserInBlacklist(User user){
        if(userService.isUserInBlacklist(user.getId())){
            logger.error(String.format("User %d from blacklist tried to make purchase", user.getId()));
            throw new ServiceException(ErrorMessages.BLACK_LIST_USER);
        }
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}

