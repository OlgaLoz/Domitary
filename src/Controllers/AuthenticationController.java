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

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	SessionMap<String, Object> session;
	private String login;
	private String password;
	private String passwordConfirmation;
	private String firstName;
	private String midName;
	private String lastName;
	private String birthday;
	private String group;
	private String params;
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

	public String getPasswordConfirmation(){
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation){
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getMidName(){
		return midName;
	}
	public void setMidName(String midName){
		this.midName = midName;
	}

	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getBirthday(){
		return birthday;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getGroup(){
		return group;
	}
	public void setGroup(String group){
		this.group = group;
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
			if (Role.Doctor.equals(user.getRole())){
			//	DispatcherControl dispatcherControl = new DispatcherControl();
			//	return dispatcherControl.getController("FindUsersToDoctor").run(request);
			}
			/*if (Role.Governor.equals(user.getRole())){
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

		return Pages.HOME_STUDENT.getPageName();
	}

	public String register() {

		if (login.trim().equals("")){
			params = "error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		User user = UserRepository.getUserByLogin(login);

		if (user != null){
			params = "error=registration&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		if (password.trim().equals("") || passwordConfirmation.trim().equals("")){
			params = "error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		if (!password.equals(passwordConfirmation)){
			params = "error=registration&state=2";
			return Pages.HOME_GUEST.getPageName();
		}

		byte[] salt;
		byte[] encryptedPassword;
		try {
			salt = encryptionService.generateSalt();
			encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
		}
		catch (Exception ex){
			params = "error=registration&state=3";
			return Pages.HOME_GUEST.getPageName();
		}

		if (firstName.trim().equals("") || midName.trim().equals("") || lastName.trim().equals("")
				|| group.trim().equals("")){
			params = "error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateOfBirth;
		try {
			java.util.Date parsed = formatter.parse(birthday);
			dateOfBirth = new java.sql.Date(parsed.getTime());
		}
		catch (ParseException ex){
			params = "error=registration&state=5";
			return Pages.HOME_GUEST.getPageName();
		}

		if (session == null){
			params = "error=authorization&state=3";
			return Pages.HOME_GUEST.getPageName();
		}

		user = new User(login, encryptedPassword, Role.Student);
		user.setSalt(salt);
		UserRepository.create(user);

		Student student = new Student();
		student.setFirstName(firstName);
		student.setMidName(midName);
		student.setLastName(lastName);
		student.setDateOfBirth(dateOfBirth);
		student.setGroupNumber(group);
		student.setUserId(user.getUserId());
		StudentRepository.create(student);

		session.put(CURRENT_USER_ATTRIBUTE, user.getUserId());
		session.put(CURRENT_ROLE_ATTRIBUTE, user.getRole());

		session.put(LOGIN_ATTRIBUTE, login);
		session.put(FIRSTNAME_ATTRIBUTE, firstName);
		session.put(MIDNAME_ATTRIBUTE, midName);
		session.put(LASTNAME_ATTRIBUTE, lastName);
		session.put(BIRTHDAY_ATTRIBUTE, birthday);
		session.put(GROUP_ATTRIBUTE, group);
		session.put(STATUS_ATTRIBUTE, student.getStudentStatus());

		return Pages.HOME_STUDENT.getPageName();
	}

	public String logout() {
		if (session != null){
			session.invalidate();
		}
		return Pages.HOME_GUEST.getPageName();
	}

}