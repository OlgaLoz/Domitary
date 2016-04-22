package Controllers;

import Interfaces.IController;

import javax.servlet.http.HttpServletRequest;

public class LogoutController implements IController {

    @Override
    public String run(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/authorization.jsp";
    }

}
