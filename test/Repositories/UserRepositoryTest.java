package Repositories;

import Model.Role;
import Model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @Test
    public void testCreate_Read() throws Exception {
        User user = new User();
        user.setLogin("User1");
        user.setPassword(40);
        user.setRole(Role.Student);
        userRepository.create(user);

        User newUser = userRepository.read(15);

        newUser.setLogin("FirstUser");
        userRepository.update(newUser);

        newUser = userRepository.read(15);
    }

    @Test
    public void testRead() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}