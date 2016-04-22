package Controllers;

import Interfaces.IController;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;

public class LogoutController implements IController {

    @Override
    public String run(HttpServletRequest request) {
        request.getSession().invalidate();
        return Pages.HOME_GUEST.getPagePath();
    }

}
