package Controllers;

import Interfaces.IController;
import Model.Role;
import Model.Student;
import Model.User;
import Repositories.StudentRepository;
import Repositories.UserRepository;
import Utils.Pages;
import Utils.PasswordEncryptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistrationController implements IController {

	private UserRepository userRepository = new UserRepository();
	private StudentRepository studentRepository = new StudentRepository();
	private PasswordEncryptionService encryptionService = new PasswordEncryptionService();

	private static final String LOGIN_ATTRIBUTE = "login";
	private static final String FIRSTNAME_ATTRIBUTE = "first_name";
	private static final String MIDNAME_ATTRIBUTE = "mid_name";
	private static final String LASTNAME_ATTRIBUTE = "last_name";
	private static final String BIRTHDAY_ATTRIBUTE = "birthday";
	private static final String GROUP_ATTRIBUTE = "group";

	@Override
	public String run(HttpServletRequest request) {

		String login = request.getParameter("display_name");

		User user = userRepository.getUserByLogin(login);

		//such user exists!
		if (user != null){
			return "/authorization.jsp";
		}

		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");

		if (!password.equals(password_confirmation)){
			return "/authorization.jsp";
		}

		byte[] salt;
		try {
			salt = encryptionService.generateSalt();
		}
		catch (NoSuchAlgorithmException ex){
			return "/authorization.jsp";
		}

		byte[] encryptedPassword;
		try {
			encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
		}
		catch (Exception ex) {
			return "/authorization.jsp";
		}

		user = new User(login, encryptedPassword, Role.Student);
		user.setSalt(salt);
		userRepository.create(user);

		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());

		String firstName = request.getParameter("first_name");
		String midName = request.getParameter("mid_name");
		String lastName = request.getParameter("last_name");
		String birthday = request.getParameter("birthday");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date expectedDateOfBirth;
		try {
			java.util.Date parsed = formatter.parse(birthday);
			expectedDateOfBirth = new java.sql.Date(parsed.getTime());
		}
		catch (ParseException ex){
			expectedDateOfBirth = null;
		}
		String group = request.getParameter("group");

		Student student = new Student();
		student.setFirstName(firstName);
		student.setMidName(midName);
		student.setLastName(lastName);
		student.setDateOfBirth(expectedDateOfBirth);
		student.setGroupNumber(group);
		student.setStatement("");
		student.setUserId(user.getUserId());
		studentRepository.create(student);

		request.setAttribute(LOGIN_ATTRIBUTE, login);
		request.setAttribute(FIRSTNAME_ATTRIBUTE, firstName);
		request.setAttribute(MIDNAME_ATTRIBUTE, midName);
		request.setAttribute(LASTNAME_ATTRIBUTE, lastName);
		request.setAttribute(BIRTHDAY_ATTRIBUTE, birthday);
		request.setAttribute(GROUP_ATTRIBUTE, group);

		return Pages.HOME_STUDENT.getPagePath();
	}

}