package online.shop.dao.utils.impl;

import online.shop.dao.utils.ResultSetExtractor;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andri on 1/17/2017.
 */
public class UserResultSetExtractor implements ResultSetExtractor<User> {
    @Override
    public User extract(ResultSet set) throws SQLException{
        return new User.Builder()
                .setId(set.getInt("UserID"))
                .setName(set.getString("name"))
                .setSurname(set.getString("surname"))
                .setEmail(set.getString("email"))
                .setPassword(set.getString("password"))
                .setBirthDate(set.getDate("birthDate"))
                .setWorker(set.getBoolean("worker"))
                .setRole(RoleType.getRole(set.getString("roleName")))
                .build();
    }
}
