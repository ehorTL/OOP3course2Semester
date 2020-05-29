package com.yehorpolishchuk.periodicals.db.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    public static Connection getConnection() throws FileNotFoundException, IOException, SQLException {
        String rootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        String appConfigPath = rootPath + "application.properties";


        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        String url = appProps.getProperty("jdbc.database.url");
        String username = appProps.getProperty("jdbc.database.username");
        String password = appProps.getProperty("jdbc.database.password");
        return DriverManager.getConnection(url, username, password);
    }
}


