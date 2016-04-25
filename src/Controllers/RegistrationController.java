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
	private static final String STATUS_ATTRIBUTE = "student_status";

	@Override
	public String run(HttpServletRequest request) {

		String login = request.getParameter("display_name");

		if (login.trim().equals("")){
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=4";
		}

		User user = userRepository.getUserByLogin(login);

		if (user != null){
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=1";
		}

		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");

		if (password.trim().equals("") || password_confirmation.trim().equals("")){
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=4";
		}

		if (!password.equals(password_confirmation)){
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=2";
		}

		byte[] salt;
		byte[] encryptedPassword;
		try {
			salt = encryptionService.generateSalt();
			encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
		}
		catch (Exception ex){
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=3";
		}

		user = new User(login, encryptedPassword, Role.Student);
		user.setSalt(salt);

		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());

		String firstName = request.getParameter("first_name");
		String midName = request.getParameter("mid_name");
		String lastName = request.getParameter("last_name");
		String birthday = request.getParameter("birthday");
		String group = request.getParameter("group");

		if (firstName.trim().equals("") || midName.trim().equals("") || lastName.trim().equals("")
				|| group.trim().equals("")){
			session.invalidate();
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=4";
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateOfBirth;
		try {
			java.util.Date parsed = formatter.parse(birthday);
			dateOfBirth = new java.sql.Date(parsed.getTime());
		}
		catch (ParseException ex){
			session.invalidate();
			return Pages.HOME_GUEST.getPagePath() + "?error=registration&state=5";
		}

		userRepository.create(user);
		Student student = new Student();
		student.setFirstName(firstName);
		student.setMidName(midName);
		student.setLastName(lastName);
		student.setDateOfBirth(dateOfBirth);
		student.setGroupNumber(group);
		student.setUserId(user.getUserId());
		studentRepository.create(student);

		session.setAttribute(LOGIN_ATTRIBUTE, login);
		session.setAttribute(FIRSTNAME_ATTRIBUTE, firstName);
		session.setAttribute(MIDNAME_ATTRIBUTE, midName);
		session.setAttribute(LASTNAME_ATTRIBUTE, lastName);
		session.setAttribute(BIRTHDAY_ATTRIBUTE, birthday);
		session.setAttribute(GROUP_ATTRIBUTE, group);
		session.setAttribute(STATUS_ATTRIBUTE, student.getStudentStatus());

		return Pages.HOME_STUDENT.getPagePath();
	}

}