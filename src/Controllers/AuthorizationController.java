package Controllers;

import Interfaces.IController;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationController implements IController {

	private static final String LOGIN_ATTRIBUTE = "login";
	private static final String FIRSTNAME_ATTRIBUTE = "first_name";
	private static final String MIDNAME_ATTRIBUTE = "mid_name";
	private static final String LASTNAME_ATTRIBUTE = "last_name";
	private static final String BIRTHDAY_ATTRIBUTE = "birthday";
	private static final String GROUP_ATTRIBUTE = "group";

	@Override
	public String run(HttpServletRequest request) {

		String login = request.getParameter("login");
		request.setAttribute(LOGIN_ATTRIBUTE, login);

		return Pages.HOME_STUDENT.getPagePath();
	}

	public boolean signIn(String login, String password){
		return false;
	}

}