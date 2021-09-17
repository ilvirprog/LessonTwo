package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private SessionFactory sessionFactory;




    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE if not exists Users (" +
                "`id` BIGINT(64) NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NULL," +
                "`lastName` VARCHAR(45) NULL," +
                "`age` TINYINT(3) NOT NULL," +
                "PRIMARY KEY (`id`));";
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана");
        } finally {
            session.close();
        }


    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE if exists users";
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        }finally {
            session.close();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.println("В таблицу был добавлен user " + name);
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Юзер c именем: " + user.getName() + " был удалён");
        } finally {
            session.close();
        }



    }

    @Override
    public List<User> getAllUsers() {
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.getTransaction();
            List<User> users = session.createCriteria(User.class).list();
            System.out.println(users);
            return users;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            // Я искал искал, но не смог найти другой вариант для очистки таблицы ... =_= грустъ
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица ощищена");
        }finally {
            session.close();
        }


    }
}
