package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.UserDao;
import online.shop.model.entity.User;
import online.shop.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andri on 1/21/2017.
 */
public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private UserServiceImpl(){}

    private static UserService instance;

    public static synchronized  UserService getInstance(){
        if(instance==null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findById(int id) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findById(id);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection()){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findUserByEmail(email);
        }
    }

    @Override
    public Optional<User> login(String email, String password) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findUserByEmail(email)
                    .filter( person-> password.equals(person.getPassword()));
        }
    }

    @Override
    public List<User> findAll() {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findAll();
        }
    }

    @Override
    public List<User> findAllCustomers() {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            return userDao.findAllCustomes();
        }
    }

    @Override
    public void create(User user) {
        try(ConnectionWrapper wrapper = daoFactory.getConnection() ){
            UserDao userDao = daoFactory.getUserDao(wrapper);
            userDao.create(user);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

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
}
