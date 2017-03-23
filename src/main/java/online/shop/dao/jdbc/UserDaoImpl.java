package online.shop.dao.jdbc;

import online.shop.dao.UserDao;
import online.shop.dao.exception.DaoException;
import online.shop.model.entity.RoleType;
import online.shop.utils.extractors.impl.UserResultSetExtractor;

import online.shop.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;;

/**
 * Created by andri on 1/5/2017.
 */
public class UserDaoImpl implements UserDao{
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String GET_ALL_USERS = "select userID, name, surname, email, password, birthDate,  role from users ";
    private static final String GET_ALL_USERS_IN_BLACKLIST = "select userID, name, surname, email, password, birthDate, role from (" +
            "blacklist join users using(userID))";
    private static final String FILTER_BY_ID = " where userID = ?;";
    private static final String FILTER_BY_EMAIL = " where email=?;";
    private static final String FILTER_BY_ROLE = " where role=?;";
    private static final String DELETE_USER = "delete from users ";
    private static final String CREATE_USER = "insert into users (`name`, `surname`, `email`, `password`, `birthDate`, `role`) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_USER = "update `online-shop`.`users` set `name`=?, `surname`=?, `email`=?, `password`=?, `birthDate`=?, `role`=? ";
    private static final String INSERT_INTO_BLACKLIST = "insert into blacklist (`userID`) values (?);";
    private static final String DELETE_FROM_BLACKLIST = "delete from blacklist where userID=?";
    private Connection connection;
    private UserResultSetExtractor extractor;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
        extractor = new UserResultSetExtractor();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_EMAIL)){
            statement.setString(1,email);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                User user = extractor.extract(set);
                result = Optional.of(user);
            }
            return result;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving user by email", ex);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                User user = extractor.extract(set);
                result = Optional.of(user);
            }
            return result;
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
            while(set.next()){
                users.add(extractor.extract(set));
            }
            return users;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all users", ex);
        }
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType) {
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS+ FILTER_BY_ROLE)){
            statement.setString(1,roleType.getRoleName());
            List<User> users = new ArrayList<>();
            ResultSet set = statement.executeQuery();
            while(set.next()){
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
            statement.setString(6, user.getRole().getRoleName());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when creating new user!", ex);
        }
    }

    @Override
    public void update(User user) {
        Objects.requireNonNull(user, "Error! Wrong user object...");
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_USER + FILTER_BY_ID)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, java.sql.Date.valueOf(user.getBirthDate().
                    toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            statement.setString(6, user.getRole().getRoleName());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when updating user!", ex);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_USER+FILTER_BY_ID)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting user!", ex);
        }
    }

    @Override
    public List<User> findAllUserInBlacklist() {
        try(Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(GET_ALL_USERS_IN_BLACKLIST)){
            List<User> users = new ArrayList<>();
            while(set.next()){
                users.add(extractor.extract(set));
            }
            return users;
        }
        catch(SQLException ex){
            throw new DaoException("dao exception occured when retrieving all users from blaclist", ex);
        }
    }

    @Override
    public void addUserToBlacklist(int id) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_INTO_BLACKLIST)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when inserting user into blacklist!", ex);
        }
    }

    @Override
    public void deleteUserFromBlacklist(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_FROM_BLACKLIST)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException("Error occured when deleting user from blacklist!", ex);
        }
    }
}
