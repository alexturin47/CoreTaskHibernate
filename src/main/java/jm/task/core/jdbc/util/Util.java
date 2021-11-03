package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB = "coretemp";
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false";


    private static SessionFactory sessionFactory;

    static{
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.driver_class", DRIVER_CLASS);
            prop.setProperty("hibernate.connection.username", USER);
            prop.setProperty("hibernate.connection.password", PASS);
            prop.setProperty("hibernate.current_session_context_class", "thread");
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            prop.setProperty("hibernate.show_sql", "true");

            Configuration configuration = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
