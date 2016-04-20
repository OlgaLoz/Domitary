package Controllers;

import Interfaces.IController;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;

public class RegistrationController implements IController {

	private static final String LOGIN_ATTRIBUTE = "login";
	private static final String FIRSTNAME_ATTRIBUTE = "first_name";
	private static final String MIDNAME_ATTRIBUTE = "mid_name";
	private static final String LASTNAME_ATTRIBUTE = "last_name";
	private static final String BIRTHDAY_ATTRIBUTE = "birthday";
	private static final String GROUP_ATTRIBUTE = "group";

	@Override
	public String run(HttpServletRequest request) {

		String login = request.getParameter("display_name");
		String firstName = request.getParameter("first_name");
		String midName = request.getParameter("mid_name");
		String lastName = request.getParameter("last_name");
		String birthday = request.getParameter("birthday");
		String group = request.getParameter("group");

		request.setAttribute(LOGIN_ATTRIBUTE, login);
		request.setAttribute(FIRSTNAME_ATTRIBUTE, firstName);
		request.setAttribute(MIDNAME_ATTRIBUTE, midName);
		request.setAttribute(LASTNAME_ATTRIBUTE, lastName);
		request.setAttribute(BIRTHDAY_ATTRIBUTE, birthday);
		request.setAttribute(GROUP_ATTRIBUTE, group);

		return Pages.HOME_STUDENT.getPagePath();
	}

	public boolean register(char userInfo){
		return false;
	}

}