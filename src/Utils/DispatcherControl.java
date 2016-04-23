package Utils;

import Interfaces.IController;
import Controllers.*;

import java.util.HashMap;
import java.util.Map;

public class DispatcherControl {

    private Map<String, IController> controllers;

    public DispatcherControl() {
        controllers = new HashMap<String, IController>();
        controllers.put("AUTHORIZATION", new AuthorizationController());
        controllers.put("REGISTRATION", new RegistrationController());
        controllers.put("LOGOUT", new LogoutController());
        controllers.put("GETSETTLERS", new GetSettlersController());
        controllers.put("FINDUSERSTODOCTOR", new DoctorSearchAllController());
        controllers.put("FINDSINGLEUSERTODOCTOR", new DoctorSearchByLastNameController());
        controllers.put("CHECKSTUDENTSBYDOCTOR", new DoctorCheckStudentsController());
    }

    public IController getController(String controller) {
        return controllers.get(getControllerNameFromUrl(controller));
    }

    private String getControllerNameFromUrl(String url){
        return url.substring(url.lastIndexOf('/') + 1).toUpperCase();
    }

}
