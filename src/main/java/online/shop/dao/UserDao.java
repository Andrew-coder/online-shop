package online.shop.dao;

import online.shop.model.entity.User;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface UserDao extends CommonDao<User> {
    User findUserByEmail(String email);
    List<User> findWorkersByRole(String role);
}
