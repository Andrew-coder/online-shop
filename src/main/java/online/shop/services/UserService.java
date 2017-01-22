package online.shop.services;

import online.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/21/2017.
 */
public interface UserService {
    Optional<User> findById(int id);
    Optional<User> findUserByEmail(String email);
    Optional<User> login (String email , String password);
    List<User> findAll();
    void create(User user);
    void update(User user);
    void delete(int id);
}
