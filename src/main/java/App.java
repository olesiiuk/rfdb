import ua.kpi.tef2.model.dao.UserDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;
import ua.kpi.tef2.model.dao.factory.JdbcDaoFactory;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;
import ua.kpi.tef2.model.service.UserService;
import ua.kpi.tef2.model.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        User user = new User("login1", "password", "USER");
        try {
            userService.saveNewUser(user);
        } catch (UserAlreadyExistsException e) {
            e.getMessage();
        }

    }

    private static void testCRUD() throws UserAlreadyExistsException {
        DaoFactory factory = new JdbcDaoFactory();
        UserDao userDao = factory.createUserDao();

        User firstUser = new User("login", "password", "USER");
        userDao.save(firstUser);
        userDao.save(new User("second", "second", "USER"));


        Optional<User> optionalUser = userDao.findOneById(3);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("didn't"));

        System.out.println("\n All users \n");
        List<User> users = userDao.findAll();
        users.forEach(System.out::println);

        user.setPassword("newPassword");
        userDao.update(user);

        Optional<User> user2 = userDao.findOneById(user.getId());
        System.out.println("\n select updated user by id \n");
        System.out.println(user2);

        userDao.delete(user);

        System.out.println("\n All users \n");

        List<User> users1 = userDao.findAll();
        users1.forEach(System.out::println);
    }
}
