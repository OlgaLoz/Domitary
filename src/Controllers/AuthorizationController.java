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
	private static final String STATUS_ATTRIBUTE = "student_status";

	@Override
	public String run(HttpServletRequest request) {

		String login = request.getParameter("login");
		User user = userRepository.getUserByLogin(login);

		if (user == null){
			return Pages.HOME_GUEST.getPagePath() + "?error=authorization";
		}

		String password = request.getParameter("password");
		boolean isValidCredentials;

		try {
			isValidCredentials = encryptionService.authenticate(password, user.getPassword(), user.getSalt());
		}
		catch (Exception ex){
			return Pages.HOME_GUEST.getPagePath() + "?error=authorization&state=1";
		}

		if (!isValidCredentials){
			return Pages.HOME_GUEST.getPagePath() + "?error=authorization";
		}

		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());
		session.setAttribute(CURRENT_ROLE_ATTRIBUTE, user.getRole());

		if (!Role.Student.equals(user.getRole())){
			if (Role.Doctor.equals(user.getRole())){
				DispatcherControl dispatcherControl = new DispatcherControl();
				return dispatcherControl.getController("FindUsersToDoctor").run(request);
			}
			if (Role.Governor.equals(user.getRole())){
				DispatcherControl dispatcherControl = new DispatcherControl();
				return dispatcherControl.getController("SearchUsersToGovernor").run(request);
			}
			if (Role.DeaneryWorker.equals(user.getRole())){
				DispatcherControl dispatcherControl = new DispatcherControl();
				return dispatcherControl.getController("ReadAllToDeanery").run(request);
			}
			return roleControl.getPagePathByRole(user.getRole());
		}

		Student student = studentRepository.getStudentByUserId(user.getUserId());

		if (student == null){
			session.invalidate();
			return Pages.HOME_GUEST.getPagePath() + "?error=authorization&state=1";
		}

		session.setAttribute(LOGIN_ATTRIBUTE, user.getLogin());
		session.setAttribute(FIRSTNAME_ATTRIBUTE, student.getFirstName());
		session.setAttribute(MIDNAME_ATTRIBUTE, student.getMidName());
		session.setAttribute(LASTNAME_ATTRIBUTE, student.getLastName());
		session.setAttribute(BIRTHDAY_ATTRIBUTE, student.getDateOfBirth().toString());
		session.setAttribute(GROUP_ATTRIBUTE, student.getGroupNumber());
		session.setAttribute(STATUS_ATTRIBUTE, student.getStudentStatus());

		return roleControl.getPagePathByRole(user.getRole());
	}
}