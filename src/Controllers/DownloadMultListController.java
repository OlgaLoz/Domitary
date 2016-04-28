package Controllers;


import Interfaces.IController;
import Model.StudentStatus;
import Utils.ExcelGenerationService;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DownloadMultListController implements IController {
    private  String DOC_PATH = "http://localhost:8080/files/";
    private final String STUDENT_STATUS = "student_status";

    @Override
    public String run(HttpServletRequest request) {
        String[] positiveResults = request.getParameterValues("chStatus");
        ArrayList<StudentStatus> statuses;

        String res = Pages.HOME_GOVERNOR.getPagePath();
        if(positiveResults != null){

            res = "";
            statuses = new ArrayList<StudentStatus>();
            for (String status : positiveResults) {
                statuses.add(StudentStatus.valueOf(status));
                res += status.toCharArray()[0];
            }
            res += "List.xlsx";

            try {
                ExcelGenerationService.createStudentsSheetsByStatuses(request.getServletContext().getRealPath("/"), res, statuses);
            }
            catch(Exception ex) {}
        }
        res = DOC_PATH + res;
        return res;
    }
}
