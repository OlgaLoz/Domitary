package Controllers;

import Interfaces.IController;
import Model.Role;
import Model.Student;
import Model.User;
import Repositories.StudentRepository;
import Repositories.UserRepository;
import Utils.Pages;
import Utils.PasswordEncryptionService;
import Utils.RoleControl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AuthenticationController extends ActionSupport implements SessionAware, IController {

	private RoleControl roleControl = new RoleControl();
	private PasswordEncryptionService encryptionService = new PasswordEncryptionService();

	private static final String LOGIN_ATTRIBUTE = "login";
	private static final String FIRSTNAME_ATTRIBUTE = "first_name";
	private static final String MIDNAME_ATTRIBUTE = "mid_name";
	private static final String LASTNAME_ATTRIBUTE = "last_name";
	private static final String BIRTHDAY_ATTRIBUTE = "birthday";
	private static final String GROUP_ATTRIBUTE = "group";
	private static final String STATUS_ATTRIBUTE = "student_status";

	private String login;
	private String password;
	private String params;
	SessionMap<String, Object> session;
	//Map session;

	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap)map;
	}

	public String getLogin(){
		return login;
	}
	public void setLogin(String login){
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getParams() {
		return params;
	}
	public void setParams(String paramName) {
		this.params = paramName;
	}

	public String login() throws Exception {

		User user = UserRepository.getUserByLogin(login);

		if (user == null){
			params = "error=authorization";
			return Pages.HOME_GUEST.getPageName();
		}

		boolean isValidCredentials;
		try {
			isValidCredentials = encryptionService.authenticate(password, user.getPassword(), user.getSalt());
		}
		catch (Exception ex){
			params = "error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		if (!isValidCredentials){
			params = "error=authorization";
			return Pages.HOME_GUEST.getPageName();
		}

		if (session == null){
			params = "error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		session.put(CURRENT_USER_ATTRIBUTE, user.getUserId());
		session.put(CURRENT_ROLE_ATTRIBUTE, user.getRole());

		if (!Role.Student.equals(user.getRole())){
			/*if (Role.Doctor.equals(user.getRole())){
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
			}*/
			return roleControl.getPageNameByRole(user.getRole());
		}

		Student student = StudentRepository.getStudentByUserId(user.getUserId());

		if (student == null){
			session.invalidate();
			params = "error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		session.put(LOGIN_ATTRIBUTE, user.getLogin());
		session.put(FIRSTNAME_ATTRIBUTE, student.getFirstName());
		session.put(MIDNAME_ATTRIBUTE, student.getMidName());
		session.put(LASTNAME_ATTRIBUTE, student.getLastName());
		session.put(BIRTHDAY_ATTRIBUTE, student.getDateOfBirth().toString());
		session.put(GROUP_ATTRIBUTE, student.getGroupNumber());
		session.put(STATUS_ATTRIBUTE, student.getStudentStatus());

		return roleControl.getPageNameByRole(user.getRole());
	}

	public String register() {
		return Pages.HOME_STUDENT.getPageName();
	}

	public String logout() {
		if (session != null){
			session.invalidate();
		}
		return Pages.HOME_GUEST.getPageName();
	}

}