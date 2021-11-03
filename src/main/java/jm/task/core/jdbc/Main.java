package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Василий", "Пупкин", (byte)50);
        userService.saveUser("Иван", "Иванов", (byte)23);
        userService.saveUser("Сергей", "Петров", (byte)35);
        userService.saveUser("Семен" , "Семенов", (byte)30);

        List<User> users = userService.getAllUsers();
        for (User user : users){
            System.out.println(user.getId() + " " + user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
