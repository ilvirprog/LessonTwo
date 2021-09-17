package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoHibernateImpl udhi = new UserDaoHibernateImpl();
        udhi.createUsersTable();
        udhi.saveUser("A","B",(byte) 25);
        udhi.saveUser("A","B",(byte) 25);
        //udhi.dropUsersTable();
        //udhi.removeUserById(1);
        //udhi.getAllUsers();
        udhi.cleanUsersTable();




    }
}
