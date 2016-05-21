package Controllers;

import Interfaces.IController;
import Model.Person;
import Model.Role;
import Model.Student;
import Model.User;
import Repositories.StudentRepository;
import Repositories.UserRepository;
import Utils.Pages;
import Utils.PasswordEncryptionService;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AuthenticationController extends ActionSupport implements IController {

	private PasswordEncryptionService encryptionService = new PasswordEncryptionService();
	private String login;
	private String password;
	private String passwordConfirmation;
	private String firstName;
	private String midName;
	private String lastName;
	private String birthday;
	private String group;
	private String params;
	private String errorMessage;
	private Person person = new Person();
	private int userId;

	public String getLogin(){
		return login;
	}
	public void setLogin(String login){
		this.login = login;
	}

	public void setPassword(String password){
		this.password = password;
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

	public String getErrorMessage(){
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Person getPerson() {
		return person;
	}

	public String login() throws Exception {

		User user = UserRepository.getUserByLogin(login);

		if (user == null){
			errorMessage = "?error=authorization";
			return Pages.HOME_GUEST.getPageName();
		}

		boolean isValidCredentials;
		try {
			isValidCredentials = encryptionService.authenticate(password, user.getPassword(), user.getSalt());
		}
		catch (Exception ex){
			errorMessage = "?error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		if (!isValidCredentials){
			errorMessage = "?error=authorization";
			return Pages.HOME_GUEST.getPageName();
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session == null){
			errorMessage = "?error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		person.setUserId(user.getUserId());
		person.setLogin(user.getLogin());
		person.setRole(user.getRole());

		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());
		session.setAttribute(CURRENT_ROLE_ATTRIBUTE, user.getRole());

		if (!Role.Student.equals(user.getRole())){
			return Pages.HOME_OTHERS.getPageName();
		}

		Student student = StudentRepository.getStudentByUserId(user.getUserId());

		if (student == null){
			session.invalidate();
			errorMessage = "?error=authorization&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		person.setStudent(student);

		return Pages.HOME_OTHERS.getPageName();
	}

	public String register() {

		if (login == null || "".trim().equals(login)){
			errorMessage = "?error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		User user = UserRepository.getUserByLogin(login);

		if (user != null){
			errorMessage = "?error=registration&state=1";
			return Pages.HOME_GUEST.getPageName();
		}

		if (password == null || passwordConfirmation == null || "".trim().equals(password) || "".trim().equals(passwordConfirmation)){
			errorMessage = "?error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		if (!password.equals(passwordConfirmation)){
			errorMessage = "?error=registration&state=2";
			return Pages.HOME_GUEST.getPageName();
		}

		byte[] salt;
		byte[] encryptedPassword;
		try {
			salt = encryptionService.generateSalt();
			encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
		}
		catch (Exception ex){
			errorMessage = "?error=registration&state=3";
			return Pages.HOME_GUEST.getPageName();
		}

		if (firstName == null || midName == null || lastName == null || group == null ||
				"".trim().equals(firstName) || "".trim().equals(midName) || "".trim().equals(lastName)
				|| "".trim().equals(group)){
			errorMessage = "?error=registration&state=4";
			return Pages.HOME_GUEST.getPageName();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateOfBirth;
		try {
			java.util.Date parsed = formatter.parse(birthday);
			dateOfBirth = new java.sql.Date(parsed.getTime());
		}
		catch (ParseException ex){
			errorMessage = "?error=registration&state=5";
			return Pages.HOME_GUEST.getPageName();
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session == null){
			errorMessage = "?error=authorization&state=3";
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

		session.setAttribute(CURRENT_USER_ATTRIBUTE, user.getUserId());
		session.setAttribute(CURRENT_ROLE_ATTRIBUTE, user.getRole());

		person.setUserId(user.getUserId());
		person.setLogin(user.getLogin());
		person.setRole(user.getRole());
		person.setStudent(student);

		return Pages.HOME_STUDENT.getPageName();
	}

	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session != null){
			session.invalidate();
		}
		return Pages.HOME_GUEST.getPageName();
	}

	public String getUser() {
		User user = UserRepository.read(userId);

		if (user == null) {
			return Pages.HOME_GUEST.getPageName();
		}

		Student student = StudentRepository.getStudentByUserId(userId);

		person.setUserId(user.getUserId());
		person.setRole(user.getRole());
		person.setLogin(user.getLogin());
		person.setStudent(student);

		return Pages.HOME_OTHERS.getPageName();
	}

}