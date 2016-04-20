package Interfaces;

import javax.servlet.http.HttpServletRequest;

public interface IController {

    String USER_ID_ATTRIBUTE = "userId";
    String run(HttpServletRequest request);
}
