package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static final String URL = "jdbc:mysql://localhost:3306/coretemp?useSSL=false";
    private static final String CURRENT_SESSION_CONTEXT_CLASS = "thread";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static final String SHOW_SQL = "true";


    private static SessionFactory sessionFactory;
    private Environment env;

    static{
        try {

            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.driver_class", DRIVER_CLASS);
            prop.setProperty("hibernate.connection.username", USER);
            prop.setProperty("hibernate.connection.password", PASS);
            prop.setProperty("hibernate.current_session_context_class", CURRENT_SESSION_CONTEXT_CLASS);
            prop.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
            prop.setProperty("hibernate.show_sql", SHOW_SQL);

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

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
