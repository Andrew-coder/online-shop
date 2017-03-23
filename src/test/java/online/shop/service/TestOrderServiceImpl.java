package online.shop.service;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.OrderDao;
import online.shop.dao.exception.DaoException;
import online.shop.dao.jdbc.ConnectionWrapperImpl;
import online.shop.model.entity.Order;
import online.shop.model.entity.User;
import online.shop.services.OrderService;
import online.shop.services.UserService;
import online.shop.services.exception.ServiceException;
import online.shop.services.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by andri on 1/30/2017.
 */
public class TestOrderServiceImpl {
    @Mock
    private DaoFactory factory;
    @Mock
    private OrderDao orderDao;
    @Mock
    private ConnectionWrapper wrapper;
    @Mock
    private UserService userService;

    private OrderService orderService;
    private Order order;
    private User user;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(factory.getOrderDao(any())).thenReturn(orderDao);
        orderService = new OrderServiceImpl(userService);
        ((OrderServiceImpl)orderService).setDaoFactory(factory);
        order = new Order();
        user = new User();
        order.setUser(user);
    }

    @Test
    public void testCreateOrderIfSuccessInTransaction(){
        when(userService.isUserInBlacklist(anyInt())).thenReturn(false);
        when(factory.getConnection()).thenReturn(wrapper);
        doNothing().when(wrapper).beginTransaction();
        doNothing().when(wrapper).commitTransaction();
        doNothing().when(orderDao).create(any());
        orderService.create(order);
        verify(wrapper, times(1)).beginTransaction();
        verify(orderDao, times(1)).create(order);
        verify(wrapper, times(1)).commitTransaction();
        verify(wrapper, times(1)).close();
    }

    @Test(expected = DaoException.class)
    public void testCreateOrderIfFailInTransaction(){
        when(userService.isUserInBlacklist(anyInt())).thenReturn(false);
        when(factory.getConnection()).thenReturn(wrapper);
        doNothing().when(wrapper).beginTransaction();
        doNothing().when(wrapper).commitTransaction();
        doThrow(DaoException.class).when(orderDao).create(any());
        orderService.create(order);
        verify(wrapper, times(1)).beginTransaction();
        verify(orderDao, times(1)).create(order);
    }

    @Test(expected = ServiceException.class)
    public void testCreateOrderIfEmpty(){
        Order order2 = null;
        orderService.create(order2);
    }

    @Test(expected = ServiceException.class)
    public void testCreateOrderIfUserInBlacklist(){
        when(userService.isUserInBlacklist(anyInt())).thenReturn(true);
        orderService.create(order);
    }
}
