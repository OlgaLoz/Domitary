package Interfaces;

import javax.servlet.http.HttpServletRequest;

public interface IController {

    String CURRENT_USER_ATTRIBUTE = "currentUser";
    String CURRENT_ROLE_ATTRIBUTE = "currentRole";
    String run(HttpServletRequest request);
}
