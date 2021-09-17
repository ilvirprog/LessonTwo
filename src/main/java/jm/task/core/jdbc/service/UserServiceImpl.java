package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoHibernateImpl hdao = new UserDaoHibernateImpl();
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        hdao.createUsersTable();
    }

    public void dropUsersTable() {
        hdao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        hdao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        hdao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hdao.getAllUsers();
    }

    public void cleanUsersTable() {
        hdao.cleanUsersTable();

    }
}
