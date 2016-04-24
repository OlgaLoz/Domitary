package Repositories;

import Model.Role;
import Model.User;
import Utils.DatabaseUtils;
import Utils.PasswordEncryptionService;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        String expectedLogin = "login", password = "password";
        PasswordEncryptionService pasEncService = new PasswordEncryptionService();
        byte[] expectedSalt = pasEncService.generateSalt();
        byte[] expectedPassword = pasEncService.getEncryptedPassword(password, expectedSalt);
        byte[] actualPassword, actualSalt;
        Role expectedRole = Role.Student;

        User expectedUser = new User();
        expectedUser.setLogin(expectedLogin);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setSalt(expectedSalt);
        expectedUser.setRole(expectedRole);

        UserRepository userRepository = new UserRepository();
        int last_inserted_id = userRepository.create(expectedUser);
        User actualUser = userRepository.read(last_inserted_id);

        userRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        actualPassword = actualUser.getPassword();
        actualSalt = actualUser.getSalt();
        for (int i = 0; i < expectedPassword.length; i++){
            Assert.assertEquals(expectedPassword[i], actualPassword[i]);
        }
        for (int i = 0; i < expectedSalt.length; i++){
            Assert.assertEquals(expectedSalt[i], actualSalt[i]);
        }
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Test
    public void testCreateUpdateReadDelete() throws Exception {
        String expectedLogin = "login", password = "password", newPassword = "password2";
        PasswordEncryptionService pasEncService = new PasswordEncryptionService();
        byte[] expectedSalt = pasEncService.generateSalt();
        byte[] expectedPassword = pasEncService.getEncryptedPassword(password, expectedSalt);
        byte[] newExpectedPassword = pasEncService.getEncryptedPassword(newPassword, expectedSalt);
        byte[] actualPassword, actualSalt;
        Role expectedRole = Role.Governor;

        User expectedUser = new User();
        expectedUser.setLogin(expectedLogin);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setSalt(expectedSalt);
        expectedUser.setRole(expectedRole);

        UserRepository userRepository = new UserRepository();
        userRepository.create(expectedUser);
        expectedUser.setPassword(newExpectedPassword);
        userRepository.update(expectedUser);

        User actualUser = userRepository.read(expectedUser.getUserId());
        userRepository.delete(expectedUser.getUserId());

        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        actualPassword = actualUser.getPassword();
        actualSalt = actualUser.getSalt();
        for (int i = 0; i < newExpectedPassword.length; i++){
            Assert.assertEquals(newExpectedPassword[i], actualPassword[i]);
        }
        for (int i = 0; i < expectedSalt.length; i++){
            Assert.assertEquals(expectedSalt[i], actualSalt[i]);
        }
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Test
    public void testCreateGetUserByLoginDelete() throws Exception {
        String expectedLogin = "login", password = "password";
        PasswordEncryptionService pasEncService = new PasswordEncryptionService();
        byte[] expectedSalt = pasEncService.generateSalt();
        byte[] expectedPassword = pasEncService.getEncryptedPassword(password, expectedSalt);
        byte[] actualPassword, actualSalt;
        Role expectedRole = Role.Doctor;

        User expectedUser = new User();
        expectedUser.setLogin(expectedLogin);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setSalt(expectedSalt);
        expectedUser.setRole(expectedRole);

        UserRepository userRepository = new UserRepository();
        int last_inserted_id = userRepository.create(expectedUser);
        expectedUser.setUserId(last_inserted_id);

        User actualUser = userRepository.getUserByLogin(expectedUser.getLogin());
        userRepository.delete(expectedUser.getUserId());

        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        actualPassword = actualUser.getPassword();
        actualSalt = actualUser.getSalt();
        for (int i = 0; i < expectedPassword.length; i++){
            Assert.assertEquals(expectedPassword[i], actualPassword[i]);
        }
        for (int i = 0; i < expectedSalt.length; i++){
            Assert.assertEquals(expectedSalt[i], actualSalt[i]);
        }
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}