package online.shop.dao.jdbc;

import online.shop.dao.UserDao;
import online.shop.dao.exception.DaoException;
import online.shop.dao.utils.impl.UserResultSetExtractor;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andri on 1/5/2017.
 */
public class UserDaoImpl implements UserDao{
    private static final String GET_ALL_USERS = "select userID, name, surname, email, password, birthDate, worker, r.roleName as role from users " +
                                                "inner join roles r on users.role=r.roleID";
    private static final String FILTER_BY_ID = " where userID = ?;";
    private static final String FILTER_BY_EMAIL = " where email=?;";
    private static final String FILTER_BY_ROLE = " inner join roles on users.role=roles.roleID where roles.roleName=?;";
    private static final String DELETE_USER = "delete from users ";
    private static final String CREATE_USER = "insert into users (`name`, `surname`, `email`, `password`, `birthDate`) VALUES (?,?,?,?,?);";
    private Connection connection;
    private UserResultSetExtractor extractor;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByEmail(String email) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_EMAIL)){
            statement.setString(1,email);
            User user = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                user = extractor.extract(set);
            }
            return user;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving user by email", ex);
        }
    }

    @Override
    public User findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            User user = null;
            ResultSet set = statement.executeQuery();
            if(set.next()){
                user = extractor.extract(set);
            }
            return user;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving user by id", ex);
        }
    }

    @Override
    public List<User> findAll() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_USERS)){
            List<User> users = new ArrayList<>();
            if(set.next()){
                users.add(extractor.extract(set));
            }
            return users;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all users", ex);
        }
    }

    @Override
    public List<User> findWorkersByRole(String role) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_ROLE)){
            statement.setString(1,role);
            List<User> users = new ArrayList<>();
            ResultSet set = statement.executeQuery();
            if(set.next()){
                users.add(extractor.extract(set));
            }
            return users;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving workers by role", ex);
        }
    }

    @Override
    public void create(User user) {
        Objects.requireNonNull(user, "Error! Wrong user object...");
        try(PreparedStatement statement = connection.prepareStatement(CREATE_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, java.sql.Date.valueOf(user.getBirthDate().
                    toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new user!", ex);
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        User user = findById(id);
        Objects.requireNonNull(user, "User with such id wasn't found");
        try(PreparedStatement statement = connection.prepareStatement(DELETE_USER+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting user!", ex);
        }
    }
}
