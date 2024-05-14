package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Test1", "Test1", "test1@gmail.com");
        user1.setCar(new Car("Kia", 1111));
        userService.add(user1);

        User user2 = new User("Test2", "Test2", "test2@gmail.com");        user2.setCar(new Car("BMW", 2222));
        userService.add(user2);

        User user3 = new User("Test3", "Test3", "test3@gmail.com");        user3.setCar(new Car("Audi",3333));
        userService.add(user3);

        User user4 = new User("Test4", "Test4", "test4@gmail.com");        user4.setCar(new Car("Niva", 4444));
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.whoIsUser("Audi", 3333);
        context.close();
    }
}