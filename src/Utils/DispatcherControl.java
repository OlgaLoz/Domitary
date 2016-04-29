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
        controllers.put("FINDUSERSTODOCTOR", new DoctorSearchAllController());
        controllers.put("FINDSINGLEUSERTODOCTOR", new DoctorSearchByLastNameController());
        controllers.put("CHECKSTUDENTSBYDOCTOR", new DoctorCheckStudentsController());
        controllers.put("GETALLBYSTATUS", new DeaneryGetAllByStatusController());
        controllers.put("READALLTODEANERY", new DeaneryFindAllController());
        controllers.put("FINDBYLASTNAMETODEANERY", new DeaneryFindByLastNameController());
        controllers.put("CHECKSTUDENTSBYDEANERY", new DeaneryCheckStudentsController());
        controllers.put("SEARCHUSERSTOGOVERNOR", new GovernorSearchAllController());
        controllers.put("SEARCHBYLASTNAMETOGOVERNOR", new GovernorSearchByLastNameController());
        controllers.put("CHECKSTUDENTSBYGOVERNOR", new GovernorCheckStudentsController());
        controllers.put("GETALLSETTLEDSTUDENTS", new GovernorGetAllSettledStudentsController());
        controllers.put("STATEMENT", new StatementController());
        controllers.put("DELETEDORMITORY",new DeleteDormitoryController());
        controllers.put("ADDDORMITORY",new AddDormitoryController());
        controllers.put("DELETEBLOCK",new DeleteBlockController());
        controllers.put("ADDBLOCK",new AddBlockController());
        controllers.put("GETBLOCKS",new GetDormitoryBlocksController());
        controllers.put("DOWNLOAD", new DownloadSingleListController());
        controllers.put("DOWNLOADM", new DownloadMultListController());
    }

    public IController getController(String controller) {
        return controllers.get(getControllerNameFromUrl(controller));
    }

    private String getControllerNameFromUrl(String url){
        return url.substring(url.lastIndexOf('/') + 1).toUpperCase();
    }

}
