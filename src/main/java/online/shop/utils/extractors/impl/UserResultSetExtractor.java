package online.shop.utils.extractors.impl;

import online.shop.utils.extractors.ResultSetExtractor;
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
        User.Builder builder= new User.Builder()
                .setId(set.getInt("UserID"))
                .setName(set.getString("name"))
                .setSurname(set.getString("surname"))
                .setEmail(set.getString("email"))
                .setPassword(set.getString("password"))
                .setBirthDate(set.getDate("birthDate"));
        String role = set.getString("role");
        if(role!=null){
            builder.setRole(RoleType.getRole(role));
        }
        return builder.build();
    }
}
