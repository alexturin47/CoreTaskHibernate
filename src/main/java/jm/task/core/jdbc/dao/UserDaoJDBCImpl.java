package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao{

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS users(id BIGINT not null AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) not null, " +
                    "lastName VARCHAR(50) not null, " +
                    "age TINYINT not null)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLSyntaxErrorException e){
            return;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getMySQLConnection()) {
            String sql = "DROP TABLE USERS";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLSyntaxErrorException e) {
            return;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try (Connection connection = Util.getMySQLConnection()){
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO USERS( name, lastName, age) VALUES('"
                    + name +"', '"
                    +  lastName + "', "
                    + age+ ")";
            statement.executeUpdate(sql);
            System.out.printf("User с именем %s - добавлен в базу \n", name);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM USERS WHERE id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list= new ArrayList<>();
        try(Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, lastName, age FROM USERS";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE USERS";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
