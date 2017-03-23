package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.UserDao;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.exception.ServiceException;
import online.shop.utils.constants.ErrorMessages;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


/**
 * Created by andri on 1/21/2017.
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public UserServiceImpl(){}

    UserServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final UserService instance = new UserServiceImpl(DaoFactory.getInstance());
    }

    public static UserService getInstance(){
        return InstanceHolder.instance;
    }

    @Override
    public Optional<User> findById(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findById(id);
        }
    }

    @Override
    public Optional<User> login(String email, String password) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findUserByEmail(email)
                    .filter( person-> password.equals(person.getPassword()));
        }
    }

    @Override
    public List<User> findAll() {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findAll();
        }
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findUsersByRole(roleType);
        }
    }

    @Override
    public void create(User user) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            checkIsUserRegistered(user.getEmail(), userDao);
            userDao.create(user);
        }
    }

    @Override
    public void update(User user) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            userDao.update(user);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addUserToBlacklist(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            if(!isUserInBlacklist(id)){
                userDao.addUserToBlacklist(id);
            }
        }
    }

    @Override
    public boolean isUserInBlacklist(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.getUserDao(wrapper);
            boolean a = userDao.findAllUserInBlacklist()
                    .stream()
                    .mapToInt(User::getId)
                    .anyMatch(userID -> userID==id);
            return a;
        }
    }

    @Override
    public void removeUserFromBlackList(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            userDao.deleteUserFromBlacklist(id);
        }
    }

    private void checkIsUserRegistered(String email, UserDao userDao){
        if(userDao.findUserByEmail(email).isPresent()){
            logger.error(ErrorMessages.EMAIL_ALREADY_EXISTS);
            throw new ServiceException(ErrorMessages.EMAIL_ALREADY_EXISTS);
        }
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
