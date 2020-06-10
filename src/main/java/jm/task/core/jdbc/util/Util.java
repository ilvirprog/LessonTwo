package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.NonRegisteringDriver;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Util {


    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "roots";
    private static Util instance;

    private  Util(){}
    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }



    public Connection getConnection(){

        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setAutoCommit(false);
            if(!connection.isClosed()){
                System.out.println("Есть соединение");
            }

            if(connection.isClosed()){
                System.out.println("Соединение закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
