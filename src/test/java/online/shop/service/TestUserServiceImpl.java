package online.shop.service;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.UserDao;
import online.shop.dao.jdbc.JdbcDaoFactory;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by andri on 1/30/2017.
 */
public class TestUserServiceImpl {
    @Mock
    private DaoFactory factory;
    @Mock
    private UserDao userDao;

    private UserService userService;
    private User user;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(factory.getUserDao(any())).thenReturn(userDao);
        userService = new UserServiceImpl();
        ((UserServiceImpl)userService).setDaoFactory(factory);
        user = new User(1,"John","Doe","John@gmail.com","12345", new Date(), RoleType.USER);
    }

    @Test
    public void testFindByID(){
        when(userDao.findById(anyInt())).thenReturn(Optional.of(user));
        Optional result = userService.findById(1);
        verify(userDao, times(1)).findById(1);
        Assert.assertEquals(user, result.get());
    }

    @Test
    public void testCreateUser(){
        doNothing().when(userDao).create(any());
        when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        userService.create(user);
        verify(userDao, times(1)).create(user);
    }
}
