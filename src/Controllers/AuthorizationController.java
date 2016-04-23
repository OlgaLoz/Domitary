package Controllers;

import Interfaces.IController;
import Model.Role;
import Model.Student;
import Model.User;
import Repositories.StudentRepository;
import Repositories.UserRepository;
import Utils.DispatcherControl;
import Utils.Pages;
import Utils.PasswordEncryptionService;
import Utils.RoleControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationController implements IController {

	private RoleControl roleControl = new RoleControl();
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


		String login = request.getParameter("login");

		User user = userRepository.getUserByLogin(login);

		//wrong login or password!
		if (user == null){
			return Pages.HOME_GUEST.getPagePath();
		}

		String password = request.getParameter("password");

		boolean isValidCredentials;
		try {
			isValidCredentials = encryptionService.authenticate(password, user.getPassword(), user.getSalt());
		}//something wrong
		catch (Exception ex){
			return Pages.HOME_GUEST.getPagePath();
		}

		//wrong login or password!
		if (!isValidCredentials){
			return Pages.HOME_GUEST.getPagePath();
		}


		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());

		if (!Role.Student.equals(user.getRole())){
			if (Role.Doctor.equals(user.getRole())){
				DispatcherControl dispatcherControl = new DispatcherControl();
				return dispatcherControl.getController("FINDUSERSTODOCTOR").run(request);
			}
			return roleControl.getPagePathByRole(user.getRole());
		}

		Student student = studentRepository.getStudentByUserId(user.getUserId());

		//something wrong
		if (student == null){
			session.invalidate();
			return Pages.HOME_GUEST.getPagePath();
		}

		session.setAttribute(LOGIN_ATTRIBUTE, login);
		session.setAttribute(FIRSTNAME_ATTRIBUTE, student.getFirstName());
		session.setAttribute(MIDNAME_ATTRIBUTE, student.getMidName());
		session.setAttribute(LASTNAME_ATTRIBUTE, student.getLastName());
		session.setAttribute(BIRTHDAY_ATTRIBUTE, student.getDateOfBirth().toString());
		session.setAttribute(GROUP_ATTRIBUTE, student.getGroupNumber());

		return roleControl.getPagePathByRole(user.getRole());
	}

}