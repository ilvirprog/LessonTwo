package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;





import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {





    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util util = Util.getInstance();
        Connection connection = util.getConnection();
        Statement statement = null;

        String sql = "CREATE TABLE if not exists Users (" +
                "  `id` BIGINT(64) NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NULL," +
                "  `lastName` VARCHAR(45) NULL," +
                "  `age` TINYINT(3) NOT NULL," +
                "  PRIMARY KEY (`id`));";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public void dropUsersTable() {
        Util util = Util.getInstance();
        Connection connection = util.getConnection();
        Statement statement = null;


        String sql = "DROP TABLE if exists users";
        System.out.println("Таблица удалена!");
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = Util.getInstance();
        Connection connection = util.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Users (name,lastName,age) VALUES (?,?,?)";


        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setLong(3,age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User " + name + " был добавлен в таблицу" );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void removeUserById(long id) {
        Util util = Util.getInstance();
        Connection connection = util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Users WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();


            System.out.println("Данные пользователя с id = " + id + " был удалён");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        Util util = Util.getInstance();
        Connection connection = util.getConnection();

        List<User> list = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT * from Users";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Util util = Util.getInstance();
        Connection connection = util.getConnection();
        Statement statement = null;
        String sql = "TRUNCATE TABLE Users";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
