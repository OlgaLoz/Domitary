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
        Role expectedRole = Role.Student;

        User expectedUser = new User();
        expectedUser.setLogin(expectedLogin);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setSalt(expectedSalt);
        expectedUser.setRole(expectedRole);

        int last_inserted_id = UserRepository.create(expectedUser);
        User actualUser = UserRepository.read(last_inserted_id);
        UserRepository.delete(last_inserted_id);

        Assert.assertEquals(last_inserted_id, actualUser.getUserId());
        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        Assert.assertArrayEquals(expectedPassword, actualUser.getPassword());
        Assert.assertArrayEquals(expectedSalt,actualUser.getSalt());
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Test
    public void testCreateUpdateReadDelete() throws Exception {
        String expectedLogin = "login", password = "password", newPassword = "password2";
        PasswordEncryptionService pasEncService = new PasswordEncryptionService();
        byte[] expectedSalt = pasEncService.generateSalt();
        byte[] expectedPassword = pasEncService.getEncryptedPassword(password, expectedSalt);
        byte[] newExpectedPassword = pasEncService.getEncryptedPassword(newPassword, expectedSalt);
        Role expectedRole = Role.Governor;

        User expectedUser = new User();
        expectedUser.setLogin(expectedLogin);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setSalt(expectedSalt);
        expectedUser.setRole(expectedRole);

        int last_inserted_id = UserRepository.create(expectedUser);
        expectedUser.setPassword(newExpectedPassword);
        UserRepository.update(expectedUser);

        User actualUser = UserRepository.read(expectedUser.getUserId());
        UserRepository.delete(expectedUser.getUserId());

        Assert.assertEquals(last_inserted_id, actualUser.getUserId());
        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        Assert.assertArrayEquals(newExpectedPassword, actualUser.getPassword());
        Assert.assertArrayEquals(expectedSalt,actualUser.getSalt());
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Test
    public void testCreateGetUserByLoginDelete() throws Exception {
        String expectedLogin = "login", password = "password";
        PasswordEncryptionService pasEncService = new PasswordEncryptionService();
        byte[] expectedSalt = pasEncService.generateSalt();
        byte[] expectedPassword = pasEncService.getEncryptedPassword(password, expectedSalt);
        Role expectedRole = Role.Doctor;

        User expectedUser = new User(expectedLogin, expectedPassword, expectedRole);
        expectedUser.setSalt(expectedSalt);

        int last_inserted_id = UserRepository.create(expectedUser);
        User actualUser = UserRepository.getUserByLogin(expectedUser.getLogin());
        UserRepository.delete(expectedUser.getUserId());

        Assert.assertEquals(last_inserted_id, actualUser.getUserId());
        Assert.assertEquals(expectedLogin, actualUser.getLogin());
        Assert.assertArrayEquals(expectedPassword, actualUser.getPassword());
        Assert.assertArrayEquals(expectedSalt,actualUser.getSalt());
        Assert.assertEquals(expectedRole, actualUser.getRole());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}